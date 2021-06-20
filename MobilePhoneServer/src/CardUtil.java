import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

class Menu{
    void showMenu1(){
        System.out.println("****移动业务菜单****");
        System.out.println("1.本月菜单查询");
        System.out.println("2.套餐余量查询");
        System.out.println("3.打印消费详单");
        System.out.println("4.套餐变更");
        System.out.println("5.办理退网");
        System.out.println("请选择（输入1～5，其他键返回上一层）：");
    }
    ArrayList<String> createNumber(ArrayList<String> a,int b){
        Random ram=new Random();
        ArrayList<String> list=new ArrayList<>();
        for(int i=1;i<10;i++){
            String g=a.get(ram.nextInt(b));
            String l=i+""+"."+g;
            System.out.println(l);
            list.add(l);
        }

       return list;

    }
    void addCard(MobileCard mobileCard){

    }
    void showRemainDetain(String number,Map<String,MobileCard> card){
        int flow=0;
        int count=0;
        int talkTime=0;
        System.out.println("****套餐余量查询****");
        System.out.println("你的卡号："+number+"套餐内剩余：");
        for (String key:card.keySet()){
            if(key.equals(number)){
                if (card.get(key).getSerPackage() instanceof TalkPackage ){
                    TalkPackage talkPackage=(TalkPackage)card.get(key).getSerPackage();
                    if(talkPackage.getSmsCount()>=card.get(key).getRealSMSCount()) {
                        count = talkPackage.getSmsCount() - card.get(key).getRealSMSCount();
                    }if(talkPackage.getTalkTime()>=card.get(key).getRealTalkTime()){
                        talkTime=talkPackage.getTalkTime()-card.get(key).getRealTalkTime();
                    }
                }else if(card.get(key).getSerPackage() instanceof NetPackage){
                    NetPackage netPackage=(NetPackage)card.get(key).getSerPackage();
                    if(netPackage.getFlow()>=card.get(key).getRealFlow()) {
                        flow = netPackage.getFlow() - card.get(key).getRealFlow();
                    }
                }else if(card.get(key).getSerPackage() instanceof SuperPackage){
                    SuperPackage superPackage=(SuperPackage) card.get(key).getSerPackage();
                    if(superPackage.getSmsCount()>=card.get(key).getRealSMSCount()) {
                        count = superPackage.getSmsCount() - card.get(key).getRealSMSCount();
                    }if(superPackage.getTalkTime()>=card.get(key).getRealTalkTime()){
                        talkTime=superPackage.getTalkTime()-card.get(key).getRealTalkTime();
                    }if(superPackage.getFlow()>=card.get(key).getRealFlow()){
                        flow=superPackage.getFlow()-card.get(key).getRealFlow();
                    }

                }
            }
        }
        System.out.println("通话时长："+talkTime+"分钟");
        System.out.println("短信条数："+count+"条");
        if(flow>=1024){
            double f=flow/1024;
            int g=(int)f;
            System.out.println("上网流量："+g+"GB"+(flow-(g*1024))+"MB");
        }else{System.out.println("上网流量："+flow+"MB");
        }

    }
    void showAmountDetail(String number, Map<String,MobileCard> card){
        int flow = card.get(number).getRealFlow();
        int count = card.get(number).getRealSMSCount();
        int talkTime = card.get(number).getRealTalkTime();
        for (String key:card.keySet()){//需要遍寻吗，还是直接用比较好？
            if(key.equals(number)){
                if (card.get(key).getSerPackage() instanceof TalkPackage ){
                    TalkPackage talkPackage=(TalkPackage)card.get(key).getSerPackage();
                    if(talkPackage.getSmsCount()<card.get(key).getRealSMSCount()) {
                        count = card.get(key).getRealSMSCount()-talkPackage.getSmsCount();
                    }if(talkPackage.getTalkTime()<card.get(key).getRealTalkTime()){
                        talkTime = card.get(key).getRealTalkTime()-talkPackage.getTalkTime();
                    }
                }else if(card.get(key).getSerPackage() instanceof NetPackage){
                    NetPackage netPackage=(NetPackage)card.get(key).getSerPackage();
                    if(netPackage.getFlow()<card.get(key).getRealFlow()) {
                        flow = card.get(key).getRealFlow()-netPackage.getFlow();
                    }
                }else if(card.get(key).getSerPackage() instanceof SuperPackage){
                    SuperPackage superPackage=(SuperPackage) card.get(key).getSerPackage();
                    if(superPackage.getSmsCount()<card.get(key).getRealSMSCount()) {
                        count = card.get(key).getRealSMSCount()-superPackage.getSmsCount();
                    }if(superPackage.getTalkTime()<card.get(key).getRealTalkTime()){
                        talkTime=card.get(key).getRealTalkTime()-superPackage.getTalkTime();
                    }if(superPackage.getFlow()<card.get(key).getRealFlow()){
                        flow = card.get(key).getRealFlow()-superPackage.getFlow();
                    }

                }

            }
        }
        DecimalFormat format=new DecimalFormat("#.0");
        double money=card.get(number).getSerPackage().getPrice()+count*0.1+talkTime*0.2+flow*0.1;
        System.out.println("合计："+format.format(money)+"元");
        System.out.println("账户余额："+format.format(card.get(number).getMoney())+"元");

    }
    void printConsumInfo(String number,Map<String,List<ConsumInfo>> consumInfos){
        List<ConsumInfo> count;
        for(String key:consumInfos.keySet()){
            if(key.equals(number)){
                count=consumInfos.get(key);
                for(int i=0;i<count.size();i++){
                   System.out.println((i+1)+".   "+count.get(i).getType()+"    "+count.get(i).getConsumData());
               }
            }
        }
    }
    List<ConsumInfo> addConsumInfo(String number,Map<String,List<ConsumInfo>> consumInfos,String s,int b){
        List<ConsumInfo> count=null;
        for(String key:consumInfos.keySet()){
            if(key.equals(number)){
                count=consumInfos.get(key);
               count.add(new ConsumInfo(number,s,b));
            }
        }
        return count;
    }
    double chargeMoney(String number,double money,MobileCard m){

        if(number.equals(m.getCardNumber())){
            money=m.getMoney()+money;
        }
        return money;
    }
    MobileCard useSoso(String number,Map<String,MobileCard> card){
        MobileCard m=new MobileCard();
        for (String key:card.keySet()){
            if(key.equals(number)){
             m=card.get(key);

            }
        }
        return m;
    }
    MobileCard talk(int b,MobileCard m) {
        int g = m.call(10,m);//通话10分钟内免费，这一步已经把做了m.setRealSMSCount(g);这一步？
        double money=0;
        //DecimalFormat format=new DecimalFormat("#.0");
        //MobileCard card=new MobileCard();
        if (m.getSerPackage() instanceof TalkPackage ){
            TalkPackage talkPackage=(TalkPackage)m.getSerPackage();
            if(m.getRealTalkTime()>=500){
                money=b*0.2;

            }else if(m.getRealTalkTime()<500&&g>=500){
                money=(g-500)*0.2;
            }
        }else if(m.getSerPackage() instanceof NetPackage){
            money=b*0.2;
        }else if(m.getSerPackage() instanceof SuperPackage){
            //,SuperPackage superPackage=(SuperPackage) m.getSerPackage();
            if(m.getRealTalkTime()>=200) {
                money=b*0.2;
            }else if(m.getRealTalkTime()<200&&g>=200){
                money=(g-200)*0.2;
            }

        }
        m.setMoney(m.getMoney()-money);
       // m.setRealTalkTime(g);
        m.setConsumAmount(m.getConsumAmount()+money);
        return m;
    }
    MobileCard sendC(int b,MobileCard m) {
        int g = m.send(b,m);
        double money=0;
        //DecimalFormat format=new DecimalFormat("#.0");
        //MobileCard card=new MobileCard();
        if (m.getSerPackage() instanceof TalkPackage ){
            if(m.getRealSMSCount()>=30){
                money=b*0.1;
            }else if(m.getRealSMSCount()<30&&g>=30){
                money=(g-30)*0.1;
            }
        }else if(m.getSerPackage() instanceof NetPackage){
            money=b*0.1;
        }else if(m.getSerPackage() instanceof SuperPackage){
            if(m.getRealTalkTime()>=50) {
                money=b*0.1;
            }else if(m.getRealTalkTime()<50&&g>=50){
                money=(g-50)*0.1;
            }

        }
        m.setMoney(m.getMoney()-money);
        //m.setRealSMSCount(g);
        m.setConsumAmount(m.getConsumAmount()+money);
        return m;
    }
    boolean isEnough(MobileCard m){
        boolean B=true;
        if(m.getMoney()<0){
            B=false;
        }
        return B;
    }
    MobileCard findCard(String number,Map<String,MobileCard> card){
        MobileCard m=new MobileCard();
        for(String key:card.keySet()){
            if(key.equals(number)){
                m=card.get(key);
            }
        }
        return m;
    }
    MobileCard netS(int b,MobileCard m) {
        int g = m.netPlay(b,m);
        double money=0;
        //DecimalFormat format=new DecimalFormat("#.0");
       // MobileCard card=new MobileCard();
        if (m.getSerPackage() instanceof TalkPackage ){
            money=b*0.1;
        }else if(m.getSerPackage() instanceof NetPackage){
            if(m.getRealFlow()>=3072){
                money=b*0.1;
            }else if(m.getRealFlow()<3072&&g>=3072){
                money=(g-3072)*0.1;
            }
        }else if(m.getSerPackage() instanceof SuperPackage){
            if(m.getRealFlow()>=1024) {
                money=b*0.1;
            }else if(m.getRealTalkTime()<1024&&g>=1024){
                money=(g-1024)*0.1;
            }

        }
        m.setMoney(m.getMoney()-money);
        //m.setRealFlow(g);
        m.setConsumAmount(m.getConsumAmount()+money);
        return m;
    }
    MobileCard changeTaocan(MobileCard m,int t){
        ServerPackage serverPackage=new ServerPackage();
        if(t==1){
            TalkPackage newPackage=(TalkPackage)serverPackage;
            newPackage.setPrice(58.0);
            newPackage.setSmsCount(30);
            newPackage.setTalkTime(500);
            serverPackage=newPackage;//不知道这样可不可以，这样serverPackage后面能够即是ServerPackage又是talkPage吗？
        }else if(t==2){
            NetPackage newPackage=(NetPackage) serverPackage;
            newPackage.setPrice(68.0);
            newPackage.setFlow(3072);
            serverPackage=newPackage;
        }else{
            SuperPackage newPackage=(SuperPackage) serverPackage;
            newPackage.setPrice(78.0);
            newPackage.setSmsCount(50);
            newPackage.setFlow(1024);
            newPackage.setTalkTime(200);
            serverPackage=newPackage;
        }

        m.setSerPackage(serverPackage);
        return m;
    }
}
public class CardUtil {
    static Map<String,MobileCard> card;
    static Map<String, List<ConsumInfo>> consumInfos;


    public static void main(String[] args) {
        System.out.println("*********欢迎使用移动业务大厅**********");
        System.out.println("1.用户登陆 2.用户注册 3.使用业务 4.话费充值 5.资费说明 6.退出系统");
        Scanner sc= new Scanner(System.in);
        int num=sc.nextInt();

        /*try{

        }catch(NullPointerException n){
            n.getStackTrace();
            System.out.println("卡号不正确，请重新输入：");
        }*/
        System.out.println("请选择："+num);
        System.out.println("谢谢使用！");
        ArrayList<String> phoneNumberGet=new ArrayList<>();
        int[] c= new int[100000000];
        Random random=new Random();
        for(int i=0;i<100000000;i++){
            c[i]=random.nextInt(100000000);
            for(int j=0;j<i;j++){
                if(c[i]==c[j]){
                    j--;
                }
            }

        }
        for(int i=0;i<c.length;i++){
            Long b=c[i]+13900000000L;
            phoneNumberGet.add(b+"");
        }
        Menu m=new Menu();
        switch(num){
            case 1:
                System.out.println("请输入手机卡号：");
                String card1=sc.next();
                try{
                    card.keySet().contains(card1);
                }catch(NullPointerException n){
                    n.getStackTrace();
                    System.out.println("卡号不正确，请重新输入：");
                }
                System.out.print(card1);
                System.out.println("请输入密码：");
                String password=sc.next();
               /* try{
                    card.keySet().contains(password);
                }catch(NullPointerException n){
                    n.getStackTrace();
                    System.out.println("密码不正确，请重新输入：");
                }*/
                System.out.println();
                m.showMenu1();
                int num1=sc.nextInt();
                switch(num1){
                    case 1:
                        System.out.println("****本月账单查询****");
                        System.out.println("你的卡号："+card1+"，当月账单：");
                        m.showAmountDetail(card1,card);
                        break;
                    case 2:
                       m.showRemainDetain(card1,card);
                        break;
                    case 3:
                        System.out.println("****"+card1+"消费详单****");
                        System.out.println("序号    类型    数据（通话（条）上网（MB）短信（条））");
                        m.printConsumInfo(card1,consumInfos);
                        break;
                    case 4:
                        System.out.println("*****套餐更换****");
                        System.out.println("1.话痨套餐 2.网虫套餐 3.超人套餐，请选择套餐（输入序号）：");
                        int t=sc.nextInt();
                        MobileCard card2=m.findCard(card1,card);
                       card2=m.changeTaocan(card2,t);
                       card.put(card1,card2) ;
                        System.out.println("套餐修改成功");
                        break;
                    case 5:
                        System.out.println("****办理退网****");
                        card.remove(card1);
                        consumInfos.remove(card1);
                        phoneNumberGet.add(card1);
                        System.out.println("办理退网成功");
                        System.out.println("谢谢使用！");
                        break;

                }while(num1!=1&num1!=2&num1!=3&num1!=4&num1!=5){
                System.out.println("返回上一级");
                break;
                }
            case 2:
                System.out.println("****可选择的卡号****");
                ArrayList<String> numGet=m.createNumber(phoneNumberGet,phoneNumberGet.size());
                System.out.println("请选择卡号（输入1～9的序号）：");
                int num2=sc.nextInt();
                String newNum=numGet.get(num2-1);
                String [] newNum1=newNum.split("\\.");
                String newNum2=newNum1[1];
                phoneNumberGet.remove(newNum);
                System.out.println("1.话痨套餐 2.网虫套餐 3.超人套餐，请选择套餐（输入序号）：");
                int t=sc.nextInt();
                ServerPackage serverPackage=new ServerPackage();
                /*if(t==1){
                    TalkPackage newPackage=(TalkPackage)serverPackage;
                    newPackage.setPrice(58.0);
                    newPackage.setSmsCount(30);
                    newPackage.setTalkTime(500);
                    serverPackage=newPackage;//不知道这样可不可以，这样serverPackage后面能够即是ServerPackage又是talkPage吗？
                }else if(t==2){
                    NetPackage newPackage=(NetPackage) serverPackage;
                    newPackage.setPrice(68.0);
                    newPackage.setFlow(3072);
                    serverPackage=newPackage;
                }else{
                    SuperPackage newPackage=(SuperPackage) serverPackage;
                    newPackage.setPrice(78.0);
                    newPackage.setSmsCount(50);
                    newPackage.setFlow(1024);
                    newPackage.setTalkTime(200);
                    serverPackage=newPackage;
                }*/

                System.out.println("请输入姓名：");
                String name=sc.nextLine();
                System.out.println("请输入密码：");
                String pw=sc.nextLine();
                System.out.println("请输入预存金额：");
                double depM=sc.nextDouble();
                MobileCard user=new MobileCard(newNum2,name,pw,serverPackage,0.0,depM,0,0,0);
                m.changeTaocan(user,t);
                card.put(newNum2,user);
                break;
            case 3:
                System.out.println("请输入手机号：");
                String num5=sc.nextLine();
                MobileCard card3=m.useSoso(num5,card);
                System.out.println("使用场景:1.打电话 2.发短信 3.上网");
                int a=sc.nextInt();
                if(a==1){
                    System.out.println("请输入通话时间:");
                    int b=sc.nextInt();
                    card3=m.talk(b,card3);
                    consumInfos.put(num5,m.addConsumInfo(num5,consumInfos,"通话",b));
                    /*if(m.talk(b,card3).getMoney()<10.0){
                        System.out.println("余额不足10元，请充值！");
                    };*/
                }else if(a==2){
                    System.out.println("请输入发短信条数:");
                    int b=sc.nextInt();
                    card3=m.sendC(b,card3);
                    consumInfos.put(num5,m.addConsumInfo(num5,consumInfos,"短信",b));
                }else if(a==3){
                    System.out.println("请输入上网时间:");
                    int b=sc.nextInt();
                    card3=m.netS(b,card3);
                    consumInfos.put(num5,m.addConsumInfo(num5,consumInfos,"上网",b));
                }
                try{
                    m.isEnough(card3);
                }catch(Exception e){

                    System.out.println("余额不足10元，请充值！");
                    e.getMessage();
                }
                break;
            case 4:
                System.out.println("请输入充值卡号：");
                String num3=sc.nextLine();
                System.out.println("请输入充值金额：");
                double num4=sc.nextDouble();
                MobileCard g=m.findCard(num3,card);
                double yuE=m.chargeMoney(num3,num4,g);
                g.setMoney(yuE);
                card.put(num3,g);//是否要先删除(num3,g)？还是可以覆盖？
                System.out.println("充值成功，当前余额为："+g.getMoney());
                break;
            case 5:
                System.out.println("****资费说明****");
                break;
            case 6:
                System.out.println("谢谢使用！");
                break;
        }
        sc.close();
    }
}
