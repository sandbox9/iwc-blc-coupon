package itwise.broadleafcommerce.coupon.dao;

import itwise.broadleafcommerce.coupon.domain.Coupon;
import itwise.broadleafcommerce.coupon.domain.CustomerCoupon;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.broadleafcommerce.common.persistence.EntityConfiguration;
import org.broadleafcommerce.profile.core.dao.CustomerDao;
import org.springframework.stereotype.Repository;

@Repository("blCouponDao")
public class CouponDaoImpl implements CouponDao {

	@PersistenceContext(unitName = "blPU")
	protected EntityManager em;

	@Resource(name = "blEntityConfiguration")
	protected EntityConfiguration entityConfiguration;

	@Resource(name = "blCustomerDao")
	protected CustomerDao customerDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<Coupon> readCouponsForCustomer(final Long customerId) {
		final Query query = em.createNamedQuery("BC_READ_COUPONS_BY_CUSTOMER_ID");
		query.setParameter("customerId", customerId);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Coupon> readCouponsForDownload() {
		final Query query = em.createNamedQuery("BC_READ_COUPONS_FOR_DOWNLOAD");
		return query.getResultList();
	}

	@Override
	public void download(final CustomerCoupon customerCoupon) {
		em.persist(customerCoupon);
	}

	@Override
	public void doApplyCoupon2OrderItem(Long customerId, Long couponId, Long orderItemId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doApplyCoupon2Order(Long customerId, Long couponId, Long orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doApplyCoupon2Fulfillment(Long customerId, Long couponId, Long orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings("unchecked")
	public Coupon readCouponByCouponId(final Long couponId) {
		if (couponId == null || "".equals(couponId)) {
			return null;
		}
		final Query query = em.createNamedQuery("BC_READ_COUPON_BY_COUPON_ID");
		query.setParameter("couponId", couponId);
		List<Coupon> coupons = query.getResultList();
		return coupons == null || coupons.isEmpty() ? null : coupons.get(0);
	}

}
