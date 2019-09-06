package cn.flyaudio.flyforum.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 叶兴运 on
 * 2019/1/23.14:50
 */
public class BrandBean extends BaseBean<BrandBean> {

    private List<ZBean> A;
    private List<ZBean> B;
    private List<ZBean> C;
    private List<ZBean> D;
    private List<ZBean> E;
    private List<ZBean> F;
    private List<ZBean> G;
    private List<ZBean> H;
    private List<ZBean> I;
    private List<ZBean> J;
    private List<ZBean> K;
    private List<ZBean> L;
    private List<ZBean> M;
    private List<ZBean> N;
    private List<ZBean> O;
    private List<ZBean> P;
    private List<ZBean> Q;
    private List<ZBean> R;
    private List<ZBean> S;
    private List<ZBean> T;
    private List<ZBean> U;
    private List<ZBean> V;
    private List<ZBean> W;
    private List<ZBean> X;
    private List<ZBean> Y;
    private List<ZBean> Z;
    public List<ZBean> list = new ArrayList<>();

    public List<ZBean> getList() {
        return list;
    }

    public void setList(List<ZBean> list) {
        this.list = list;
    }

    public List<ZBean> getA() {
        return A;
    }

    public void setA(List<ZBean> a) {
        A = a;
    }


    public List<ZBean> getB() {
        return B;
    }

    public void setB(List<ZBean> b) {
        B = b;
    }

    public List<ZBean> getC() {
        return C;
    }

    public void setC(List<ZBean> c) {
        C = c;
    }

    public List<ZBean> getD() {
        return D;
    }

    public void setD(List<ZBean> d) {
        D = d;
    }

    public List<ZBean> getE() {
        return E;
    }

    public void setE(List<ZBean> e) {
        E = e;
    }

    public List<ZBean> getF() {
        return F;
    }

    public void setF(List<ZBean> f) {
        F = f;
    }

    public List<ZBean> getG() {
        return G;
    }

    public void setG(List<ZBean> g) {
        G = g;
    }

    public List<ZBean> getH() {
        return H;
    }

    public void setH(List<ZBean> h) {
        H = h;
    }

    public List<ZBean> getI() {
        return I;
    }

    public void setI(List<ZBean> i) {
        I = i;
    }

    public List<ZBean> getJ() {
        return J;
    }

    public void setJ(List<ZBean> j) {
        J = j;
    }

    public List<ZBean> getK() {
        return K;
    }

    public void setK(List<ZBean> k) {
        K = k;
    }

    public List<ZBean> getL() {
        return L;
    }

    public void setL(List<ZBean> l) {
        L = l;
    }

    public List<ZBean> getM() {
        return M;
    }

    public void setM(List<ZBean> m) {
        M = m;
    }

    public List<ZBean> getN() {
        return N;
    }

    public void setN(List<ZBean> n) {
        N = n;
    }

    public List<ZBean> getO() {
        return O;
    }

    public void setO(List<ZBean> o) {
        O = o;
    }

    public List<ZBean> getP() {
        return P;
    }

    public void setP(List<ZBean> p) {
        P = p;
    }

    public List<ZBean> getQ() {
        return Q;
    }

    public void setQ(List<ZBean> q) {
        Q = q;
    }

    public List<ZBean> getR() {
        return R;
    }

    public void setR(List<ZBean> r) {
        R = r;
    }

    public List<ZBean> getS() {
        return S;
    }

    public void setS(List<ZBean> s) {
        S = s;
    }

    public List<ZBean> getT() {
        return T;
    }

    public void setT(List<ZBean> t) {
        T = t;
    }

    public List<ZBean> getU() {
        return U;
    }

    public void setU(List<ZBean> u) {
        U = u;
    }

    public List<ZBean> getV() {
        return V;
    }

    public void setV(List<ZBean> v) {
        V = v;
    }

    public List<ZBean> getW() {
        return W;
    }

    public void setW(List<ZBean> w) {
        W = w;
    }

    public List<ZBean> getX() {
        return X;
    }

    public void setX(List<ZBean> x) {
        X = x;
    }

    public List<ZBean> getY() {
        return Y;
    }

    public void setY(List<ZBean> y) {
        Y = y;
    }

    public List<ZBean> getZ() {
        return Z;
    }

    public void setZ(List<ZBean> z) {
        Z = z;
    }

    public static class ZBean {
        /**
         * uuid : 622db23f193d11e98ee77cd30aeb138e
         * brandName : 长城
         * avatar : 2222
         * createTime : 2019-01-16 11:18:24.0
         * vehicleTypeList : [{"uuid":"123123wwwwweeeerrr","vehicleType":"A3"}]
         */

        private String uuid;
        private String brandName;
        private String avatar;
        private String createTime;
        private List<VehicleTypeListBean> vehicleTypeList;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<VehicleTypeListBean> getVehicleTypeList() {
            return vehicleTypeList;
        }

        public void setVehicleTypeList(List<VehicleTypeListBean> vehicleTypeList) {
            this.vehicleTypeList = vehicleTypeList;
        }

        public static class VehicleTypeListBean {
            /**
             * uuid : 123123wwwwweeeerrr
             * vehicleType : A3
             */

            private String uuid;
            private String vehicleType;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getVehicleType() {
                return vehicleType;
            }

            public void setVehicleType(String vehicleType) {
                this.vehicleType = vehicleType;
            }
        }
    }
}
