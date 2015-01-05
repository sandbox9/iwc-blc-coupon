package itwise.broadleafcommerce.coupon.dao;

import itwise.broadleafcommerce.coupon.domain.CustomerCoupon;
import itwise.broadleafcommerce.coupon.domain.OfferCoupon;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.broadleafcommerce.common.persistence.EntityConfiguration;
import org.broadleafcommerce.core.offer.dao.OfferDaoImpl;
import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.profile.core.dao.CustomerDao;
import org.springframework.stereotype.Repository;

@Repository("blOfferDao")
public class CouponDaoImpl extends OfferDaoImpl implements CouponDao {

	@PersistenceContext(unitName = "blPU")
	protected EntityManager em;

	@Resource(name = "blEntityConfiguration")
	protected EntityConfiguration entityConfiguration;

	@Resource(name = "blCustomerDao")
	protected CustomerDao customerDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<OfferCoupon> readCouponsForCustomer(final Long customerId) {
		final Query query = em.createNamedQuery("BC_READ_COUPONS_BY_CUSTOMER_ID");
		query.setParameter("customerId", customerId);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OfferCoupon> readCouponsForDownload() {
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
	public OfferCoupon readCouponByCouponId(final Long couponId) {
		if (couponId == null || "".equals(couponId)) {
			return null;
		}
		final Query query = em.createNamedQuery("BC_READ_COUPON_BY_COUPON_ID");
		query.setParameter("couponId", couponId);
		List<OfferCoupon> coupons = query.getResultList();
		return coupons == null || coupons.isEmpty() ? null : coupons.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public OfferCoupon readCouponByProductId(Long productId) {
		if (productId == null || "".equals(productId)) {
			return null;
		}
		final Query query = em.createNamedQuery("BC_READ_COUPON_BY_PRODUCT_ID");
		query.setParameter("productId", productId);
		List<OfferCoupon> coupons = query.getResultList();
		return coupons == null || coupons.isEmpty() ? null : coupons.get(0);
	}

	/*
	 * Offer에서 Coupon은 제외
	 */
	@Override
	public List<Offer> readOffersByAutomaticDeliveryType() {
		List<Offer> offers = super.readOffersByAutomaticDeliveryType();

		// Skip Coupon
		Iterator<Offer> itr = offers.iterator();
		while (itr.hasNext()) {
			Offer offer = itr.next();
			if (offer instanceof OfferCoupon) {
				// itr.remove();
			}
		}
		return offers;
	}

	@Override
	public List<OfferCoupon> readCouponByAvailable(Long customerId) {
		final Query query = em.createNamedQuery("BC_READ_COUPONS_BY_CUSTOMER_ID");
		query.setParameter("customerId", customerId);
		return query.getResultList();
	}

	@Override
	public List<OfferCoupon> readCouponByCustomer(Long customerId) {
		final Query query = em.createNamedQuery("BC_READ_COUPONS_BY_CUSTOMER_ID");
		query.setParameter("customerId", customerId);
		return query.getResultList();
	}
}
