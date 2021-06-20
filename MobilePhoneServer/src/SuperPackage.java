public class SuperPackage extends ServerPackage {
    int talkTime;
    int smsCount;
    int flow;

    public SuperPackage() {
    }

    public int getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    @Override
    public String showInfo() {
        return super.showInfo();
    }
    public int call(int miniCount,MobileCard card){
        return miniCount;
    }
    public int send(int count,MobileCard card){
        return count;
    }
    public int netPlay(int flow,MobileCard card){
        return flow;
    }

}
