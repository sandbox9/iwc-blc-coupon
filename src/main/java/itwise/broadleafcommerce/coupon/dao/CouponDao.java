package itwise.broadleafcommerce.coupon.dao;

import itwise.broadleafcommerce.coupon.domain.Coupon;
import itwise.broadleafcommerce.coupon.domain.CustomerCoupon;

import java.util.List;

public interface CouponDao {

	List<Coupon> readCouponsForCustomer(Long customerId);
	List<Coupon> readCouponsForDownload();
	Coupon readCouponByCouponId(Long couponId);

	void download(CustomerCoupon customerCoupon);

	void doApplyCoupon2OrderItem(Long customerId, Long couponId, Long orderItemId);
	void doApplyCoupon2Order(Long customerId, Long couponId, Long orderId);
	void doApplyCoupon2Fulfillment(Long customerId, Long couponId, Long fulfillmentId);
}
