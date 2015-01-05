package itwise.broadleafcommerce.coupon.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.broadleafcommerce.core.order.domain.OrderImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "IWC_ORDER_FOR_COUPON")
public class OrderForCouponImpl extends OrderImpl implements OrderForCoupon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = OfferCouponImpl.class)
	@JoinTable(name = "IWC_ORDER_COUPON_XREF", joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "OFFER_ID", referencedColumnName = "OFFER_ID"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "blOrderElements")
	@Id
	protected List<OfferCoupon> addedOfferCoupons = new ArrayList<OfferCoupon>();

	@Override
	public List<OfferCoupon> getAddedCoupons() {
		return addedOfferCoupons;
	}

	@Override
	public void addOfferCoupon(OfferCoupon coupon) {
		getAddedCoupons().add(coupon);
	}

}
