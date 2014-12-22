package itwise.broadleafcommerce.coupon.domain;

import java.io.Serializable;
import java.util.Date;

import org.broadleafcommerce.core.offer.domain.Offer;

public interface Coupon extends Offer, Serializable {

	String getCouponName();

	void setCouponName(String name);

	Date getDownloadStartDate();

	void setDownloadStartDate(Date date);

	Date getDownloadEndDate();

	void setDownloadEndDate(Date date);

	int getAvailableDownload();

	void setAvailableDownload(int i);
}
