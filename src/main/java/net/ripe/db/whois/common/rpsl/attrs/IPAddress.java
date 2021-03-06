package net.ripe.db.whois.common.rpsl.attrs;

import net.ripe.db.whois.common.domain.CIString;
import net.ripe.db.whois.common.ip.IpInterval;
import net.ripe.db.whois.common.ip.Ipv4Resource;

public class IPAddress {
    private final IpInterval ipInterval;

    public IPAddress(final IpInterval ipInterval) {
        this.ipInterval = ipInterval;
    }

    public IpInterval getIpInterval() {
        return ipInterval;
    }

    @Override
    public boolean equals(final Object o) { //TODO: untested
    	if(o == this)
    		return true;
    	if(o == null || !(o instanceof IPAddress))
    		return false;
    	else {
    		final IPAddress that = (IPAddress) o;
    		return ipInterval.equals(that.ipInterval);
    	}
    }
    
    @Override
    public String toString() { //TODO: no tests yet
    	return ipInterval.toString();
    }
    
    @Override
    public int hashCode() { //TODO: no tests yet
    	return ipInterval.hashCode();
    }
    
    public static IPAddress parse(final CIString value) {
        return parse(value.toString());
    }

    public static IPAddress parse(final String value) {
        final IpInterval ipInterval;
        try {
            ipInterval = IpInterval.parse(value);
        } catch (IllegalArgumentException e) {
            throw new AttributeParseException("Invalid ip address", value);
        }

        // TODO: [AH] add support for this operation in IpInterval
        final int maxPrefix = ipInterval instanceof Ipv4Resource ? 32 : 128;
        if (ipInterval.getPrefixLength() != maxPrefix) {
            throw new AttributeParseException("Not a single address", value);
        }

        return new IPAddress(ipInterval);
    }
}
