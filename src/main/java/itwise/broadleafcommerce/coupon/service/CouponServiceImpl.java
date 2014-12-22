package itwise.broadleafcommerce.coupon.service;

import itwise.broadleafcommerce.coupon.dao.CouponDao;
import itwise.broadleafcommerce.coupon.domain.Coupon;
import itwise.broadleafcommerce.coupon.domain.CustomerCoupon;
import itwise.broadleafcommerce.coupon.domain.CustomerCouponImpl;

import java.util.List;

import javax.annotation.Resource;

import org.broadleafcommerce.common.util.TransactionUtils;
import org.broadleafcommerce.core.offer.dao.OfferDao;
import org.broadleafcommerce.core.offer.service.OfferServiceImpl;
import org.broadleafcommerce.core.order.dao.OrderDao;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("blCouponService")
public class CouponServiceImpl extends OfferServiceImpl implements CouponService {

	@Resource(name = "blCouponDao")
	protected CouponDao couponDao;

	@Resource(name = "blOrderDao")
	protected OrderDao orderDao;

	@Resource(name = "blOfferDao")
	protected OfferDao offerDao;

	@Override
	public List<Coupon> findCouponsForCustomer(Customer customer) {
		return couponDao.readCouponsForCustomer(customer.getId());
	}

	@Override
	@Transactional(TransactionUtils.DEFAULT_TRANSACTION_MANAGER)
	public void downloadCoupon(Customer customer, Coupon coupon) {
		CustomerCoupon customerCoupon = new CustomerCouponImpl();
		customerCoupon.setCustomer(customer);
		customerCoupon.setCoupon(coupon);

		couponDao.download(customerCoupon);
	}

	@Override
	public List<Coupon> findCouponForDownload() {
		return couponDao.readCouponsForDownload();
	}

	@Override
	public Coupon findCouponByCouponId(Long couponId) {
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
		// TODO Auto-generated method stub
		couponDao.doApplyCoupon2Fulfillment(customer.getId(), couponId, fulfillmentId);
	}

	public void getCriteriaTarget() {

	}

}
