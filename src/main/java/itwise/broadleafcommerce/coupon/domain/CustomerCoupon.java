package itwise.broadleafcommerce.coupon.domain;

import java.io.Serializable;
import java.util.Date;

import org.broadleafcommerce.profile.core.domain.Customer;


public interface CustomerCoupon extends Serializable {

	OfferCoupon getCoupon();
	void setCoupon(OfferCoupon coupon);
	
	Customer getCustomer();
	void setCustomer(Customer customer);
	
	Date getDownloadDate();
	void setDownloadDate(Date date);
	
}
