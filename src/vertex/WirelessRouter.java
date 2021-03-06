package vertex;

import Exception.Vertex.VertexAttributeException;

import java.util.Arrays;

/**
 * RI ip的每一个部分的值都在[0,255]的范围内
 */
public class WirelessRouter extends Vertex {
    //    private int[] ip = new int[4];
    private String ip = "";

    public WirelessRouter(String label) {
        super(label);
    }

    private void checkRep() {
        String values[] = ip.split("\\.");
        for (String value : values) {
            int ipValue = Integer.parseInt(value);
            assert ipValue >= 0 && ipValue <= 255;
        }
    }

    String getIp() {
        return ip;
    }

    @Override
    public void fillVertexInfo(String[] args) throws NumberFormatException, VertexAttributeException {
        if (args.length == 1) {
            try {
                String values[] = args[0].split("\\.");
                for (String value : values) {
                    int ipValue = Integer.parseInt(value);
                    if (ipValue < 0 || ipValue > 255) // ip的每一个部分的值都在[0,255]的范围内
                        throw new VertexAttributeException(getLabel());
                }
                ip = args[0];
            } catch (NumberFormatException e) {
                throw new VertexAttributeException(getLabel());
            }
        } else {
            throw new VertexAttributeException(getLabel());
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof WirelessRouter && ((WirelessRouter) obj).getLabel().equals(this.getLabel()) && ((WirelessRouter) obj).getIp().equals(this.getIp());
    }

    @Override
    public int hashCode() {
        Object[] objects = new Object[2];
        objects[0] = this.getLabel();
        objects[1] = ip;
        return Arrays.hashCode(objects);
    }
}
