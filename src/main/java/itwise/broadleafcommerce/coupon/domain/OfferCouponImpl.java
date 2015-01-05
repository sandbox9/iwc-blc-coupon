package itwise.broadleafcommerce.coupon.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.broadleafcommerce.common.persistence.ArchiveStatus;
import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.domain.OfferImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "IWC_COUPON")
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "blOrderElements")
@SQLDelete(sql = "UPDATE IWC_COUPON SET ARCHIVED = 'Y' WHERE OFFER_ID = ?")
public class OfferCouponImpl implements OfferCoupon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(generator = "OfferCouponId")
//	@GenericGenerator(name = "OfferCouponId", strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator", parameters = {
//			@Parameter(name = "segment_value", value = "OfferCouponImpl"),
//			@Parameter(name = "entity_name", value = "itwise.broadleafcommerce.coupon.domain.OfferCouponImpl") })
//	@Column(name = "COUPON_ID")
//	// @AdminPresentation(friendlyName = "OfferCouponImpl_Offer_Coupon_Id")
//	protected Long id;

	@Column(name = "COUPON_NAME", nullable = false)
	@Index(name = "OFFERCOUPON_NAME_INDEX", columnNames = { "COUPON_NAME" })
	// @AdminPresentation(friendlyName = "OfferCouponImpl_Coupon_Name", order =
	// 1000, prominent = true, gridOrder = 1000)
	protected String couponName;

	@Column(name = "COUPON_DOWNLOAD_START_DATE", nullable = false)
	protected Date downloadStartDate;

	@Column(name = "COUPON_DOWNLOAD_END_DATE", nullable = false)
	protected Date downloadEndDate;

	@Column(name = "COUPON_AVAILABLE_DOWNLOAD", nullable = false)
	protected int availableDownload;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "addedOfferCoupons", targetEntity = OrderForCouponImpl.class)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "blOrderElements")
	private List<OrderForCoupon> orders = new ArrayList<OrderForCoupon>();

	@OneToOne(targetEntity = OfferImpl.class, optional = false)
	@JoinColumn(name = "OFFER_ID")
	@Index(name = "OFFERCODE_OFFER_INDEX", columnNames = { "OFFER_ID" })
	@Id
	// @AdminPresentation(friendlyName = "OfferCodeImpl_Offer", order=2000,
//	prominent = true, gridOrder = 2000)
//	@AdminPresentationToOneLookup()
	protected Offer offer;

	@Embedded
	protected ArchiveStatus archiveStatus = new ArchiveStatus();

	public OfferCouponImpl() {

	}

	@Override
	public Date getDownloadStartDate() {
		return this.downloadStartDate;
	}

	@Override
	public void setDownloadStartDate(Date date) {
		this.downloadStartDate = date;
	}

	@Override
	public Date getDownloadEndDate() {
		return this.downloadEndDate;
	}

	@Override
	public void setDownloadEndDate(Date date) {
		this.downloadEndDate = date;
	}

	@Override
	public int getAvailableDownload() {
		return this.availableDownload;
	}

	@Override
	public void setAvailableDownload(int i) {
		this.availableDownload = i;
	}

	@Override
	public String getCouponName() {
		return couponName;
	}

	@Override
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	@Override
	public Offer getOffer() {
		return offer;
	}

	@Override
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	/**
	 * @return the orders
	 */
	public List<OrderForCoupon> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(List<OrderForCoupon> orders) {
		this.orders = orders;
	}

	@Override
	public void setArchived(Character archived) {
		if (archiveStatus == null) {
			archiveStatus = new ArchiveStatus();
		}
		archiveStatus.setArchived(archived);
	}

	@Override
	public Character getArchived() {
		if (archiveStatus == null) {
			archiveStatus = new ArchiveStatus();
		}
		return archiveStatus.getArchived();
	}

	@Override
	public boolean isActive() {
		// boolean datesActive;
		// // If the start date for this offer code has not been set, just
		// delegate to the offer to determine if the code is
		// // active rather than requiring the user to set offer code dates as
		// well
		// if (offerCodeStartDate == null) {
		// datesActive = DateUtil.isActive(getOffer().getStartDate(),
		// getOffer().getEndDate(), true);
		// } else {
		// datesActive = DateUtil.isActive(offerCodeStartDate, offerCodeEndDate,
		// true);
		// }
		// return datesActive && 'Y' != getArchived();
		return true;
	}
}
