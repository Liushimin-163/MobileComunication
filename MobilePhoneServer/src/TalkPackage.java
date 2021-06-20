public class TalkPackage extends ServerPackage  {
    int talkTime;
    int smsCount;

    public TalkPackage(int talkTime, int smsCount) {
        this.talkTime = talkTime;
        this.smsCount = smsCount;
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
}
