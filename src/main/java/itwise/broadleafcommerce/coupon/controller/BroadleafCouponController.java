package itwise.broadleafcommerce.coupon.controller;

import itwise.broadleafcommerce.coupon.CouponException;
import itwise.broadleafcommerce.coupon.domain.OfferCoupon;
import itwise.broadleafcommerce.coupon.service.CouponService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.broadleafcommerce.common.util.BLCMessageUtils;
import org.broadleafcommerce.common.web.controller.BroadleafAbstractController;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.exception.IllegalCartOperationException;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.core.web.order.CartState;
import org.broadleafcommerce.core.web.service.UpdateCartService;
import org.broadleafcommerce.profile.web.core.CustomerState;
import org.springframework.ui.Model;

public class BroadleafCouponController extends BroadleafAbstractController {

	private final Logger logger = Logger.getLogger(this.getClass());

	// @Resource(name = "blCatalogService")
	// protected CatalogService catalogService;

	// @Resource(name = "blOrderService")
	// protected OrderService orderService;

	// @Resource(name = "blOfferService")
	// protected OfferService offerService;

	@Resource(name = "blUpdateCartService")
	protected UpdateCartService updateCartService;

	// @Resource(name = "blOrderToPaymentRequestDTOService")
	// protected OrderToPaymentRequestDTOService dtoTranslationService;

	@Resource(name = "blCouponService")
	protected CouponService couponService;

	private static String couponDetailView = "coupon/coupon";
	private static String couponListView = "coupon/coupon";
	private static String couponPageRedirect = "redirect:/coupons";
	private static String myCouponsView = "account/myCoupon";
	private static String myApplyCouponView = "account/applyCoupon";

	/**
	 * 
	 */
	public BroadleafCouponController() {

	}

	/**
	 * Render Coupon Page TODO: Change PricingException -> CouponException
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws PricingException
	 */
	public String availableDownload(HttpServletRequest request, HttpServletResponse response, Model model) throws PricingException {
		List<OfferCoupon> coupons = couponService.findCouponForDownload();
		model.addAttribute("coupons", coupons);
		return getCouponListView();
	}

	private String getCouponListView() {
		return this.couponListView;
	}

	/**
	 * 
	 * 
	 * @return the couponView
	 */
	public static String getCouponDetailView() {
		return couponDetailView;
	}

	/**
	 * @return the couponPageRedirect
	 */
	public static String getCouponPageRedirect() {
		return couponPageRedirect;
	}

	/**
	 * TODO CouponOperationException Define 해야함
	 * 
	 * @param ex
	 * @return
	 */
	public Map<String, String> handleIllegalCartOpException(IllegalCartOperationException ex) {
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("error", "illegalCartOperation");
		returnMap.put("exception", BLCMessageUtils.getMessage(ex.getType()));
		return returnMap;
	}

	public String doDownload(HttpServletRequest request, HttpServletResponse response, Long couponId) throws CouponException {
		OfferCoupon coupon = couponService.lookupCouponById(couponId);
		if (coupon == null) {
			throw new CouponException("존재하지 않는 쿠폰입니다.");
		}

		List<OfferCoupon> coupons = couponService.findCouponsForCustomer(CustomerState.getCustomer());
		if (coupons.contains(coupon)) {
			throw new CouponException("이미 다운로드 받은 쿠폰입니다.");
		}

		couponService.downloadCoupon(CustomerState.getCustomer(), coupon);

		// TODO: 나중에 변수로 변경, 고정시키면 안됨
		return "redirect:/coupons";
	}

	/**
	 * @return the myCoupons
	 */
	public static String getMyCouponsView() {
		return myCouponsView;
	}

	/**
	 * @param myCouponsView
	 *            the myCoupons to set
	 */
	public static void setMyCoupons(String myCouponsView) {
		BroadleafCouponController.myCouponsView = myCouponsView;
	}

	public void appyCoupon2Order(HttpServletRequest request, HttpServletResponse response, Long couponId, Long orderId) {
		couponService.applyCoupon2Order(CustomerState.getCustomer(), couponId, orderId);
	}

	public void appyCoupon2OrderItem(HttpServletRequest request, HttpServletResponse response, Long couponId, Long orderItemId) {
		couponService.applyCoupon2OrderItem(CustomerState.getCustomer(), couponId, orderItemId);
	}

	public void appyCoupon2Fulfillment(HttpServletRequest request, HttpServletResponse response, Long couponId, Long fulfillmentId) {
		couponService.applyCoupon2Fulfillment(CustomerState.getCustomer(), couponId, fulfillmentId);
	}

	public String viewMyCoupon(HttpServletRequest request, Model model) {
		List<OfferCoupon> coupons = couponService.findCouponsForCustomer(CustomerState.getCustomer());
		model.addAttribute("coupons", coupons);
		return getMyCouponsView();
	}

	public String viewApplyCoupon(HttpServletRequest request, Model model) {
		model.addAttribute("coupons", couponService.findCouponsForCustomer(CustomerState.getCustomer()));
		return myApplyCouponView;
	}

	public String availableCoupon(HttpServletRequest request, HttpServletResponse response, Model model) throws PricingException {
		Order cart = CartState.getCart();
		List<OfferCoupon> coupons = couponService.findCouponForApply(cart);
		model.addAttribute("coupons", coupons);
		return getMyCouponApplyView();
	}

	private String getMyCouponApplyView() {
		return myApplyCouponView;
	}
}
