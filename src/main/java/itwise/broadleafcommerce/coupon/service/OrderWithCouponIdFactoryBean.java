package itwise.broadleafcommerce.coupon.service;

import org.springframework.beans.factory.FactoryBean;

public class OrderWithCouponIdFactoryBean implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		String myOrderId = null;
		return myOrderId;
	}

	@Override
	public Class getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
