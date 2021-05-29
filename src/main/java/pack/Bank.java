package pack;



public class Bank {

    private int nbanque;
    private float ncompte, ncompteeuro;

    public Bank() {

    }

    public Bank(int nbanque, float ncompte, float ncompteeuro) {
        this.nbanque = nbanque;
        this.ncompte = ncompte;
        this.ncompteeuro = ncompteeuro;
    }

    public int getNbanque() {
        return nbanque;
    }

    public void setNbanque(int nbanque) {
        this.nbanque = nbanque;
    }

    public float getNcompte() {
        return ncompte;
    }

    public void setNcompte(float ncompte) {
        this.ncompte = ncompte;
    }

    public float getNcompteeuro() {
        return ncompteeuro;
    }

    public void setNcompteeuro(float ncompteeuro) {
        this.ncompteeuro = ncompteeuro;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "nbanque=" + nbanque +
                ", ncompte=" + ncompte +
                ", ncompteeuro=" + ncompteeuro +
                '}';
    }
}
