package itwise.broadleafcommerce.coupon.dao;

import itwise.broadleafcommerce.coupon.domain.CustomerCoupon;
import itwise.broadleafcommerce.coupon.domain.OfferCoupon;

import java.util.List;

public interface CouponDao {

	List<OfferCoupon> readCouponsForCustomer(Long customerId);

	List<OfferCoupon> readCouponsForDownload();

	OfferCoupon readCouponByCouponId(Long couponId);

	void download(CustomerCoupon customerCoupon);

	void doApplyCoupon2OrderItem(Long customerId, Long couponId, Long orderItemId);

	void doApplyCoupon2Order(Long customerId, Long couponId, Long orderId);

	void doApplyCoupon2Fulfillment(Long customerId, Long couponId, Long fulfillmentId);

	OfferCoupon readCouponByProductId(Long productId);

	List<OfferCoupon> readCouponByCustomer(Long customerId);

	List<OfferCoupon> readCouponByAvailable(Long customerId);
}
