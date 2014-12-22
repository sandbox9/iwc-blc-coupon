package itwise.broadleafcommerce.coupon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.broadleafcommerce.core.offer.domain.OfferImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="IWC_COUPON")
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blOffers")
public class CouponImpl extends OfferImpl implements Coupon{

	@Column(name = "COUPON_DOWNLOAD_START_DATE", nullable = false)
	protected Date downloadStartDate;
	
	@Column(name = "COUPON_DOWNLOAD_END_DATE", nullable = false)
	protected Date downloadEndDate;
	
	@Column(name = "COUPON_AVAILABLE_DOWNLOAD", nullable = false)
	protected int availableDownload;
	
	public CouponImpl() {
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCouponName(String name) {
		// TODO Auto-generated method stub
		
	}
}
