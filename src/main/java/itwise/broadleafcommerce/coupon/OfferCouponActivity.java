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

import java.util.List;

import javax.annotation.Resource;

import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.domain.OfferCode;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.OrderService;
import org.broadleafcommerce.core.pricing.service.workflow.OfferActivity;
import org.broadleafcommerce.core.workflow.ProcessContext;

public class OfferCouponActivity extends OfferActivity {

	@Resource(name = "blCouponService")
	protected CouponService couponService;

	@Resource(name = "blOrderService")
	protected OrderService orderService;

	@Override
	public ProcessContext<Order> execute(ProcessContext<Order> context) throws Exception {
		Order order = context.getSeedData();
		List<OfferCode> offerCodes = offerService.buildOfferCodeListForCustomer(order.getCustomer());
		if (offerCodes != null && !offerCodes.isEmpty()) {
			order = orderService.addOfferCodes(order, offerCodes, false);
		}

		// 고객 쿠폰 가져와서 order에 추가한다.
//		List<OfferCoupon> coupons = couponService.buildCouponListForCustomer(order);
//		if (coupons != null && !coupons.isEmpty()) {
//			order = couponService.addCoupons(order, coupons, false);
//		}

//		일단 적용가능한 모든 Offer를 가져온다.
		List<Offer> offers = offerService.buildOfferListForOrder(order);
		order = offerService.applyAndSaveOffersToOrder(offers, order);
		context.setSeedData(order);

//		Long couponId;
		//TODO: 적용할 쿠폰 가져오기
//		OfferCoupon coupon = couponService.findCouponByCustomerCoupon(couponId, order.getCustomer());

//		// TODO: 현재 주문에 쿠폰 적용 하기
//		if (coupons != null && !coupons.isEmpty()) {
//			order = orderService.addCoupons(order, coupons, false);
//		}

//		TODO: 현재 주문 상품에 쿠폰 적용 하기
//		TODO: 현재 주문에 배송비 할인 쿠폰 적용하기

		// List<Coupon> coupons =
		// couponService.buildCouponListForCustomer(order.getCustomer());
		// if (coupons != null && !coupons.isEmpty()) {
		// order = orderService.addOfferCodes(order, coupons, false);
		// }

		// List<Offer> offers = couponService.buildOfferListForOrder(order);
		// order = orderService.applyAndSaveCouponToOrder(offers, order);
//		context.setSeedData(order);

		return context;
	}
}
