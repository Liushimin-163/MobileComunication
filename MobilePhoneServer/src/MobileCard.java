public class MobileCard implements SendServer,NetServer,CallServer{
    private String cardNumber;
    private String userName;
    private String passWord;
    private ServerPackage serPackage;
   private double consumAmount;
   private double money;
   private int realTalkTime;
    private int realSMSCount;
   private int realFlow;

    public MobileCard() {
    }

    public MobileCard(String cardNumber, String userName, String passWord, ServerPackage serPackage, double consumAmount, double money, int realTalkTime, int realSMSCount, int realFlow) {
        this.cardNumber = cardNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.serPackage = serPackage;
        this.consumAmount = consumAmount;
        this.money = money;
        this.realTalkTime = realTalkTime;
        this.realSMSCount = realSMSCount;
        this.realFlow = realFlow;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public ServerPackage getSerPackage() {
        return serPackage;
    }

    public void setSerPackage(ServerPackage serPackage) {
        this.serPackage = serPackage;
    }

    public double getConsumAmount() {
        return consumAmount;
    }

    public void setConsumAmount(double consumAmount) {
        this.consumAmount = consumAmount;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getRealTalkTime() {
        return realTalkTime;
    }

    public void setRealTalkTime(int realTalkTime) {
        this.realTalkTime = realTalkTime;
    }

    public int getRealSMSCount() {
        return realSMSCount;
    }

    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }

    public int getRealFlow() {
        return realFlow;
    }

    public void setRealFlow(int realFlow) {
        this.realFlow = realFlow;
    }

    public String showMeg() {
        return "MobileCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", serPackage=" + serPackage +
                ", consumAmount=" + consumAmount +
                ", money=" + money +
                ", realTalkTime=" + realTalkTime +
                ", realSMSCount=" + realSMSCount +
                ", realFlow=" + realFlow +
                '}';
    }

    @Override
    public int netPlay(int flow, MobileCard card) {
       realFlow= card.getRealFlow()+flow;
        return realFlow;
    }

    @Override
    public int send(int count, MobileCard card) {
         realSMSCount=card.getRealSMSCount()+count;
        /*if (card.getSerPackage() instanceof TalkPackage ){
            TalkPackage talkPackage=(TalkPackage)card.getSerPackage();
            if(talkPackage.getSmsCount()>=count){
                realSMSCount=0;
            }else{
                realSMSCount=talkPackage.getSmsCount()-count;
            }
        }else if(card.getSerPackage() instanceof NetPackage){

            realSMSCount=count;
        }else{
            SuperPackage superPackage=(SuperPackage) card.getSerPackage();
            if(superPackage.getFlow()>=count){
                realSMSCount=0;
            }else{
                realSMSCount=superPackage.getSmsCount()-count;
            }

        }*/
        return realSMSCount;
    }

    @Override
    public int call(int miniCount, MobileCard card) {
        realTalkTime=card.getRealTalkTime()-miniCount;
        return realTalkTime;
    }
}
