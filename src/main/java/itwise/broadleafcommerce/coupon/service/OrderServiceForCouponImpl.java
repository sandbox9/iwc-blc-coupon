package itwise.broadleafcommerce.coupon.service;

import itwise.broadleafcommerce.coupon.domain.OfferCoupon;
import itwise.broadleafcommerce.coupon.domain.OrderForCoupon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.OrderServiceImpl;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.springframework.transaction.annotation.Transactional;

public class OrderServiceForCouponImpl extends OrderServiceImpl {

	@Resource(name = "blCouponService")
	protected CouponService couponService;

//	@Resource(name = "blOrderService")
//	protected OrderService orderService;

//	@Override
	@Transactional("blTransactionManager")
	public Order addCoupon(Order order, OfferCoupon coupon, boolean priceOrder) throws PricingException {
		ArrayList<OfferCoupon> coupons = new ArrayList<OfferCoupon>();
		coupons.add(coupon);
		return addCoupons(order, coupons, priceOrder);
	}

//	@Override
	@Transactional("blTransactionManager")
	public Order addCoupons(Order order, List<OfferCoupon> coupons, boolean priceOrder) throws PricingException {
		preValidateCartOperation(order);
		Set<OfferCoupon> addedCoupons = couponService.getUniqueCouponsFromOrder(order);

		if (coupons != null && !coupons.isEmpty()) {
			for (OfferCoupon coupon : coupons) {
				// TODO: give some sort of notification that adding the offer
				// code to the order was unsuccessful
				OrderForCoupon order4Coupon = (OrderForCoupon) order;
//				if (!order4Coupon.getAddedCoupons().contains(coupon) && !addedCoupons.contains(coupon.getCoupon())) {
				if (!order4Coupon.getAddedCoupons().contains(coupon)) {
//					if(!couponService.verifyMaxCustomerUsageThreshold(order.getCustomer(), coupon)) {
//						throw new OfferMaxUseExceededException("The customer has used this offer code more than the maximum allowed number of times.");
//					}
					order4Coupon.getAddedCoupons().add(coupon);
				}
			}
			order = super.save(order, priceOrder);
		}

		return order;
	}

//	@Override
//	private void preValidateCartOperation(Order order) {
//		// TODO Auto-generated method stub
//	}

}
