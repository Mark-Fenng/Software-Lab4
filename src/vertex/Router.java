package vertex;

import Exception.Vertex.VertexAttributeException;

import java.util.Arrays;

public class Router extends Vertex {
    //    private int[] ip = new int[4];
    private String ip = "";

    public Router(String label) {
        super(label);
    }

    String getIp() {
        return ip;
    }

    @Override
    public void fillVertexInfo(String[] args) throws NumberFormatException, VertexAttributeException {
        if (args.length == 1) {
//            String values[] = args[0].split(".");
//            for (int i = 0; i < values.length; i++) {
//                ip[i] = Integer.parseInt(values[i]);
//            }
            ip = args[0];
        } else {
            throw new VertexAttributeException(getLabel());
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Router && ((Router) obj).getLabel().equals(this.getLabel()) && ((Router) obj).getIp().equals(this.getIp());
    }

    @Override
    public int hashCode() {
        Object[] objects = new Object[2];
        objects[0] = this.getLabel();
        objects[1] = ip;
        return Arrays.hashCode(objects);
    }
}
