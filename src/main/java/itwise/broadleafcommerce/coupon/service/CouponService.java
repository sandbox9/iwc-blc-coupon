package itwise.broadleafcommerce.coupon.service;

import itwise.broadleafcommerce.coupon.domain.Coupon;

import java.util.List;

import org.broadleafcommerce.profile.core.domain.Customer;

public interface CouponService {
	public List<Coupon> findCouponsForCustomer(Customer customer);

	public void downloadCoupon(Customer customer, Coupon couponId);

	public List<Coupon> findCouponForDownload();

	public Coupon findCouponByCouponId(Long couponId);

	public void applyCoupon2Order(Customer customer, Long orderId, Long orderId2);

	public void applyCoupon2OrderItem(Customer customer, Long orderItemId, Long orderItemId2);

	public void applyCoupon2Fulfillment(Customer customer, Long fulfillmentId, Long fulfillmentId2);
}
