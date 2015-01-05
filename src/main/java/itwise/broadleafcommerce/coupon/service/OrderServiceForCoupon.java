package itwise.broadleafcommerce.coupon.service;

import itwise.broadleafcommerce.coupon.domain.OfferCoupon;

import java.util.List;

import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;

public interface OrderServiceForCoupon {

	Order addCoupon(Order order, OfferCoupon coupon, boolean priceOrder) throws PricingException;

	Order addCoupons(Order order, List<OfferCoupon> coupons, boolean priceOrder) throws PricingException;

}
