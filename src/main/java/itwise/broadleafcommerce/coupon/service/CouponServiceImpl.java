package itwise.broadleafcommerce.coupon.service;

import itwise.broadleafcommerce.coupon.ItemOfferProcessor4CouponImpl;
import itwise.broadleafcommerce.coupon.dao.CouponDao;
import itwise.broadleafcommerce.coupon.domain.CustomerCoupon;
import itwise.broadleafcommerce.coupon.domain.CustomerCouponImpl;
import itwise.broadleafcommerce.coupon.domain.OfferCoupon;
import itwise.broadleafcommerce.coupon.domain.OrderForCoupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.util.TransactionUtils;
import org.broadleafcommerce.core.offer.domain.CandidateOrderOffer;
import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.service.OfferService;
import org.broadleafcommerce.core.offer.service.OfferServiceExtensionManager;
import org.broadleafcommerce.core.offer.service.OfferServiceUtilities;
import org.broadleafcommerce.core.offer.service.discount.domain.PromotableCandidateItemOffer;
import org.broadleafcommerce.core.offer.service.discount.domain.PromotableCandidateOrderOffer;
import org.broadleafcommerce.core.offer.service.discount.domain.PromotableItemFactory;
import org.broadleafcommerce.core.offer.service.discount.domain.PromotableOrder;
import org.broadleafcommerce.core.offer.service.processor.FulfillmentGroupOfferProcessor;
import org.broadleafcommerce.core.offer.service.processor.OrderOfferProcessor;
import org.broadleafcommerce.core.order.dao.OrderDao;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.order.service.OrderService;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("blCouponService")
public class CouponServiceImpl implements CouponService {

	private static final Log LOG = LogFactory.getLog(CouponServiceImpl.class);

	@Resource(name = "blOfferDao")
	protected CouponDao couponDao;

	@Resource(name = "blOrderDao")
	protected OrderDao orderDao;

	@Resource(name = "blOfferDao")
	protected CouponDao offerDao;

	@Resource(name = "blOrderService")
	protected OrderService orderService;

	@Resource(name = "blOfferService")
	protected OfferService offerService;

	@Resource(name = "blOrderOfferProcessor")
	protected OrderOfferProcessor orderOfferProcessor;

	@Resource(name = "blItemOfferProcessor")
	protected ItemOfferProcessor4CouponImpl itemOfferProcessor;

	@Resource(name = "blFulfillmentGroupOfferProcessor")
	protected FulfillmentGroupOfferProcessor fulfillmentGroupOfferProcessor;

	@Resource(name = "blPromotableItemFactory")
	protected PromotableItemFactory promotableItemFactory;

	@Resource(name = "blOfferServiceExtensionManager")
	protected OfferServiceExtensionManager extensionManager;

	@Resource(name = "blOfferServiceUtilities")
	protected OfferServiceUtilities offerServiceUtilities;

	@Override
	public List<OfferCoupon> findCouponsForCustomer(Customer customer) {
		return couponDao.readCouponsForCustomer(customer.getId());
	}

	@Override
	@Transactional(TransactionUtils.DEFAULT_TRANSACTION_MANAGER)
	public void downloadCoupon(Customer customer, OfferCoupon coupon) {
		CustomerCoupon customerCoupon = new CustomerCouponImpl();
		customerCoupon.setCustomer(customer);
		customerCoupon.setCoupon(coupon);

		couponDao.download(customerCoupon);
	}

	@Override
	public List<OfferCoupon> findCouponForDownload() {
		return couponDao.readCouponsForDownload();
	}

	@Override
	public OfferCoupon lookupCouponById(Long couponId) {
		return couponDao.readCouponByCouponId(couponId);
	}

	@Override
	public void applyCoupon2OrderItem(Customer customer, Long couponId, Long orderItemId) {
		// TODO Auto-generated method stub
		couponDao.doApplyCoupon2OrderItem(customer.getId(), couponId, orderItemId);

	}

	@Override
	public void applyCoupon2Order(Customer customer, Long couponId, Long orderId) {
		couponDao.doApplyCoupon2Order(customer.getId(), couponId, orderId);
	}

	@Override
	public void applyCoupon2Fulfillment(Customer customer, Long couponId, Long fulfillmentId) {
		couponDao.doApplyCoupon2Fulfillment(customer.getId(), couponId, fulfillmentId);
	}

	public void getCriteriaTarget() {

	}

	@Override
	public OfferCoupon findCouponByProductId(Long productId) {
		return couponDao.readCouponByProductId(productId);
	}

	@Override
	public List<OfferCoupon> buildCouponListForCustomer(Order order) {
		Customer customer = order.getCustomer();
		return couponDao.readCouponByCustomer(customer.getId());
	}

	@Override
	@Transactional("blTransactionManager")
	public Order addCoupon(Order order, OfferCoupon coupon, boolean priceOrder) throws PricingException {
		ArrayList<OfferCoupon> coupons = new ArrayList<OfferCoupon>();
		coupons.add(coupon);
		return addCoupons(order, coupons, priceOrder);
	}

	@Override
	@Transactional("blTransactionManager")
	public Order addCoupons(Order order, List<OfferCoupon> coupons, boolean priceOrder) throws PricingException {
		orderService.preValidateCartOperation(order);
		preValidateCartOperation(order);

		OrderForCoupon orderForCoupon = (OrderForCoupon) order;

		Set<Offer> addedOffers = offerService.getUniqueOffersFromOrder(order);

		if (coupons != null && !coupons.isEmpty()) {
			for (OfferCoupon coupon : coupons) {
				// TODO: give some sort of notification that adding the offer
				// code to the order was unsuccessful
//				if (!order.getAddedCoupons().contains(coupon) && !addedOffers.contains(coupon.getOffer())) {
////					if(!offerService.verifyMaxCustomerUsageThreshold(order.getCustomer(), coupon)) {
////						throw new OfferMaxUseExceededException("The customer has used this offer code more than the maximum allowed number of times.");
////					}
				orderForCoupon.getAddedCoupons().add(coupon);
//				}
			}
			order = orderService.save(order, priceOrder);
		}

		return order;
	}

	private void preValidateCartOperation(Order order) {
		// TODO Auto-generated method stub
	}

	@Override
	public Set<OfferCoupon> getUniqueCouponsFromOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfferCoupon findCouponByCustomerCoupon(Long couponId, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OfferCoupon> findCouponForApply(Order order) {

		Long customerId = order.getCustomer().getId();
		List<OfferCoupon> coupons = couponDao.readCouponByCustomer(customerId);

		//내 쿠폰중에 Item 조건에 해당하는 쿠폰 찾기
		List<CandidateOrderOffer> candidateOrderOffers = order.getCandidateOrderOffers();

		//내 쿠폰중에 장바구니 조건에 해당하는 쿠폰 찾기
		return couponDao.readCouponByCustomer(customerId);
	}

	@Override
	public List<OfferCoupon> buildCouponListForOrder(Order order) {

		Long customerId = order.getCustomer().getId();
		return couponDao.readCouponByCustomer(customerId);
	}

	@Override
	public Map<OrderItem, OfferCoupon> buildCouponListForOrderItem(Order order) {
		Map<OrderItem, OfferCoupon> availableCoupons = new HashMap<OrderItem, OfferCoupon>();
		PromotableOrder promotableOrder = promotableItemFactory.createPromotableOrder(order, false);

		List<OfferCoupon> coupons = couponDao.readCouponByCustomer(order.getCustomer().getId());
		List<Offer> offers = new ArrayList<Offer>();
		for (OfferCoupon coupon : coupons) {
			offers.add(coupon.getOffer());
		}
		List<Offer> filteredOffers = orderOfferProcessor.filterOffers(offers, order.getCustomer());

		if ((filteredOffers == null) || (filteredOffers.isEmpty())) {
			if (LOG.isTraceEnabled()) {
				LOG.trace("No offers applicable to this order.");
			}
		} else {
			List<PromotableCandidateOrderOffer> qualifiedOrderOffers = new ArrayList<PromotableCandidateOrderOffer>();
			List<PromotableCandidateItemOffer> qualifiedItemOffers = new ArrayList<PromotableCandidateItemOffer>();
			HashMap<OrderItem, Offer> result = new HashMap<OrderItem, Offer>();
			itemOfferProcessor.filterOffersForCoupon(promotableOrder, filteredOffers, qualifiedOrderOffers, qualifiedItemOffers, result);

			for (OrderItem orderItem : order.getOrderItems()) {
				if (result.containsKey(orderItem)) {
					for (OfferCoupon coupon : coupons) {
						if (coupon.getOffer().equals(result.get(orderItem))) {
							availableCoupons.put(orderItem, coupon);
							break;
						}
					}
				} else {
					availableCoupons.put(orderItem, null);
				}
			}
			if (!(qualifiedItemOffers.isEmpty() && qualifiedOrderOffers.isEmpty())) {

			}
		}

		return availableCoupons;
	}

	@Override
	public List<OfferCoupon> buildCouponListForShip(Order order) {
		// TODO Auto-generated method stub
		return null;
	}
}
