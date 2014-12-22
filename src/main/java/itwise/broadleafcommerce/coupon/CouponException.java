package itwise.broadleafcommerce.coupon;

import org.broadleafcommerce.common.exception.ServiceException;

public class CouponException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponException() {
		// do nothing
	}

	public CouponException(Throwable cause) {
		super(cause);
	}

	public CouponException(String message) {
		super(message);
	}

	public CouponException(String message, Throwable cause) {
		super(message, cause);
	}

}
