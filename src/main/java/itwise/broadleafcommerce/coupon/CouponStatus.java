package itwise.broadleafcommerce.coupon;

import itwise.broadleafcommerce.coupon.domain.Coupon;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.broadleafcommerce.common.BroadleafEnumerationType;


public class CouponStatus implements Serializable, BroadleafEnumerationType {


    private static final long serialVersionUID = 1L;

    private static final LinkedHashMap<String, CouponStatus> TYPES = new LinkedHashMap<String, CouponStatus>();

    /**
     * Represents a wishlist. This also usually means that the {@link Coupon} has its {@link Coupon#getName()} set although
     * not required
     */
    public static final CouponStatus PREPARED = new CouponStatus("PREPARED", "Prepared");
    public static final CouponStatus AVAILABLE = new CouponStatus("AVAILABLE", "Available");
    public static final CouponStatus EXPIRED = new CouponStatus("EXPIRED", "Expired");
    public static final CouponStatus NOSTOCK = new CouponStatus("NOSTOCK", "NoStock");
    public static final CouponStatus GIVEN = new CouponStatus("GIVEN", "Given");
    
    public static CouponStatus getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String friendlyType;

    public CouponStatus() {
        //do nothing
    }

    public CouponStatus(final String type, final String friendlyType) {
        this.friendlyType = friendlyType;
        setType(type);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getFriendlyType() {
        return friendlyType;
    }

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        CouponStatus other = (CouponStatus) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
