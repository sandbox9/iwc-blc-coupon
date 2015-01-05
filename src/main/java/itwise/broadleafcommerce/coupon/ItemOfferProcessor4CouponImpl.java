package itwise.broadleafcommerce.coupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.domain.OfferItemCriteria;
import org.broadleafcommerce.core.offer.service.discount.CandidatePromotionItems;
import org.broadleafcommerce.core.offer.service.discount.domain.PromotableCandidateItemOffer;
import org.broadleafcommerce.core.offer.service.discount.domain.PromotableCandidateOrderOffer;
import org.broadleafcommerce.core.offer.service.discount.domain.PromotableOrder;
import org.broadleafcommerce.core.offer.service.discount.domain.PromotableOrderItem;
import org.broadleafcommerce.core.offer.service.processor.ItemOfferProcessorImpl;
import org.broadleafcommerce.core.offer.service.type.OfferType;
import org.broadleafcommerce.core.order.domain.OrderItem;

public class ItemOfferProcessor4CouponImpl extends ItemOfferProcessorImpl {

	protected static final Log LOG = LogFactory.getLog(ItemOfferProcessor4CouponImpl.class);

	@Override
	public CandidatePromotionItems couldOfferApplyToOrderItems(Offer offer, List<PromotableOrderItem> promotableOrderItems) {
		return super.couldOfferApplyToOrderItems(offer, promotableOrderItems);
	}

	@Override
	public boolean couldOrderItemMeetOfferRequirement(OfferItemCriteria criteria, PromotableOrderItem orderItem) {
		return super.couldOrderItemMeetOfferRequirement(criteria, orderItem);
	}

	@Override
	public Boolean applyItemOfferExtension(PromotableOrder order, PromotableCandidateItemOffer itemOffer) {
		return super.applyItemOfferExtension(order, itemOffer);
	}

	@Override
	public boolean offerMeetsSubtotalRequirements(PromotableOrder order, PromotableCandidateItemOffer itemOffer) {
		return super.offerMeetsSubtotalRequirements(order, itemOffer);
	}

	public void filterOffersForCoupon(PromotableOrder order, List<Offer> filteredOffers, List<PromotableCandidateOrderOffer> qualifiedOrderOffers,
			List<PromotableCandidateItemOffer> qualifiedItemOffers, HashMap<OrderItem, Offer> result) {
		// set order subTotal price to total item price without adjustments
		order.setOrderSubTotalToPriceWithoutAdjustments();

//		offer 갯수만큼 Loop
		for (Offer offer : filteredOffers) {
			if (offer.getType().equals(OfferType.ORDER)) {
//	            filterOrderLevelOffer(order, qualifiedOrderOffers, offer);
			} else if (offer.getType().equals(OfferType.ORDER_ITEM)) {
				filterItemLevelOfferForCoupon(order, qualifiedItemOffers, offer, result);
			}
		}
	}

	public void filterItemLevelOfferForCoupon(PromotableOrder order, List<PromotableCandidateItemOffer> qualifiedItemOffers, Offer offer,
			HashMap<OrderItem, Offer> result) {
		boolean isNewFormat = CollectionUtils.isNotEmpty(offer.getQualifyingItemCriteria()) || CollectionUtils.isNotEmpty(offer.getTargetItemCriteria());
		boolean itemLevelQualification = false;
		boolean offerCreated = false;

		List<PromotableOrderItem> discountableOrderItems = order.getDiscountableOrderItems();

		for (PromotableOrderItem promotableOrderItem : order.getDiscountableOrderItems()) {
			LOG.debug("---" + promotableOrderItem.getOrderItem().getName() + " " + offer.getName());
			boolean couldOfferApplyToOrder = couldOfferApplyToOrder(offer, order, promotableOrderItem);
			if (couldOfferApplyToOrder) {
				CandidatePromotionItems candidates = null;
				List<PromotableOrderItem> promotableOrderItems = new ArrayList<PromotableOrderItem>();
				promotableOrderItems.add(promotableOrderItem);
				candidates = couldOfferApplyToOrderItems(offer, promotableOrderItems);
				PromotableCandidateItemOffer candidateOffer = null;

				if (candidates.isMatchedTarget() && candidates.isMatchedQualifier()) {
					LOG.debug(promotableOrderItem.getOrderItem().getName() + " " + offer.getName());
					candidateOffer = createCandidateItemOffer(qualifiedItemOffers, offer, order);
					candidateOffer.getCandidateTargetsMap().putAll(candidates.getCandidateTargetsMap());
					result.put(promotableOrderItem.getOrderItem(), offer);
					break;
				}
			}
		} //end for
	}
}
