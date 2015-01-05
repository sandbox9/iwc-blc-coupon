package itwise.broadleafcommerce.coupon.domain;

import java.io.Serializable;
import java.util.Date;

import org.broadleafcommerce.common.persistence.Status;
import org.broadleafcommerce.core.offer.domain.Offer;

public interface OfferCoupon extends Serializable, Status {

	String getCouponName();

	void setCouponName(String name);

	Date getDownloadStartDate();

	void setDownloadStartDate(Date date);

	Date getDownloadEndDate();

	void setDownloadEndDate(Date date);

	int getAvailableDownload();

	void setAvailableDownload(int i);

	public Offer getOffer();

	public void setOffer(Offer offer);
}
