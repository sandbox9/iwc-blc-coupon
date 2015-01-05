package itwise.broadleafcommerce.coupon.service;

import itwise.broadleafcommerce.coupon.domain.OfferCoupon;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.domain.OrderItem;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.profile.core.domain.Customer;

public interface CouponService {
	public List<OfferCoupon> findCouponsForCustomer(Customer customer);

	public void downloadCoupon(Customer customer, OfferCoupon couponId);

	public List<OfferCoupon> findCouponForDownload();

	public OfferCoupon lookupCouponById(Long couponId);

	public OfferCoupon findCouponByProductId(Long productId);

	public void applyCoupon2Order(Customer customer, Long orderId, Long orderId2);

	public void applyCoupon2OrderItem(Customer customer, Long orderItemId, Long orderItemId2);

	public void applyCoupon2Fulfillment(Customer customer, Long fulfillmentId, Long fulfillmentId2);

	public List<OfferCoupon> buildCouponListForCustomer(Order order);

	Order addCoupon(Order order, OfferCoupon coupon, boolean priceOrder) throws PricingException;

	Order addCoupons(Order order, List<OfferCoupon> coupons, boolean priceOrder) throws PricingException;

	public Set<OfferCoupon> getUniqueCouponsFromOrder(Order order);

	public OfferCoupon findCouponByCustomerCoupon(Long couponId, Customer customer);

	List<OfferCoupon> findCouponForApply(Order cart);

	public List<OfferCoupon> buildCouponListForOrder(Order order);

	public Map<OrderItem, OfferCoupon> buildCouponListForOrderItem(Order order);

	public List<OfferCoupon> buildCouponListForShip(Order order);
}
