package itwise.broadleafcommerce.coupon.domain;

import java.util.List;

import org.broadleafcommerce.core.order.domain.Order;

public interface OrderForCoupon extends Order {

	List<OfferCoupon> getAddedCoupons();

	void addOfferCoupon(OfferCoupon coupon);
}
