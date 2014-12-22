package itwise.broadleafcommerce.coupon.domain;

import java.io.Serializable;
import java.util.Date;

import org.broadleafcommerce.profile.core.domain.Customer;


public interface CustomerCoupon extends Serializable {

	Coupon getCoupon();
	void setCoupon(Coupon coupon);
	
	Customer getCustomer();
	void setCustomer(Customer customer);
	
	Date getDownloadDate();
	void setDownloadDate(Date date);
	
}
