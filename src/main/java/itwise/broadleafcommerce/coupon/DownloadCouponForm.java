package itwise.broadleafcommerce.coupon;

import java.io.Serializable;

public class DownloadCouponForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long couponId;

	/**
	 * @return the couponId
	 */
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId the couponId to set
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
}
