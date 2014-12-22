package itwise.broadleafcommerce.coupon;

/*
 * #%L
 * BroadleafCommerce Framework
 * %%
 * Copyright (C) 2009 - 2013 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import itwise.broadleafcommerce.coupon.service.CouponService;

import javax.annotation.Resource;

import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.OrderService;
import org.broadleafcommerce.core.workflow.BaseActivity;
import org.broadleafcommerce.core.workflow.ProcessContext;

public class CouponOfferActivity extends BaseActivity<ProcessContext<Order>> {

	@Resource(name = "blCouponService")
	protected CouponService couponService;

	@Resource(name = "blOrderService")
	protected OrderService orderService;

	@Override
	public ProcessContext<Order> execute(ProcessContext<Order> context) throws Exception {
		Order order = context.getSeedData();
		// List<OfferCode> offerCodes =
		// couponService.buildOfferCodeListForCustomer(order.getCustomer());
		// order = orderService.addOfferCodes(order, coupons, false);

		// List<Coupon> coupons =
		// couponService.buildCouponListForCustomer(order.getCustomer());
		// if (coupons != null && !coupons.isEmpty()) {
		// order = orderService.addOfferCodes(order, coupons, false);
		// }

		// List<Offer> offers = couponService.buildOfferListForOrder(order);
		// order = couponService.applyAndSaveOffersToOrder(offers, order);
		context.setSeedData(order);

		return context;
	}

}
