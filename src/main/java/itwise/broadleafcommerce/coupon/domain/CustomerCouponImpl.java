package itwise.broadleafcommerce.coupon.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.domain.CustomerImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;



@Entity
@Table(name="IWC_CUSTOMER_COUPON")
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
public class CustomerCouponImpl implements CustomerCoupon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "CustomerCouponId")
	@GenericGenerator(
		name="CustomerCouponId",
		strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
		parameters = {
			@Parameter(name="segment_value", value="CustomerCouponImpl"),
			@Parameter(name="entity_name", value="org.broadleafcommerce.profile.core.domain.CustomerCouponImpl")
		}
	)
	@Column(name = "CUSTOMER_COUPON_ID")
	protected Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = CustomerImpl.class, optional=false)
	@JoinColumn(name = "CUSTOMER_ID")
	protected Customer customer;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = CouponImpl.class, optional=false)
	@JoinColumn(name = "OFFER_ID")
	@Index(name="CUSTOMERCOUPON_OFFER_INDEX", columnNames={"OFFER_ID"})
	protected Coupon coupon;
	
	
	@Column(name = "DONWLOAD_DATE")
	protected Date downloadDate;

	public CustomerCouponImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Coupon getCoupon() {
		return this.coupon;
	}

	@Override
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public Date getDownloadDate() {
		return this.downloadDate;
	}

	@Override
	public void setDownloadDate(Date date) {
		this.downloadDate = date;
	}
}
