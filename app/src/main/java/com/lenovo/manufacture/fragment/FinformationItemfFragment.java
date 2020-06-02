package com.lenovo.manufacture.fragment;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adapter.InfomationAdapter;
import com.lenovo.manufacture.base.BaseFragment;
import com.lenovo.manufacture.pojo.Information;
import com.lenovo.manufacture.util.RecycleviewItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.stream.Collectors;

public class FinformationItemfFragment extends BaseFragment {

    private RecyclerView inforv;
    private AlertDialog alertDialog;
    private Gson gson = new Gson();
    private boolean MTF;
    private List<Information.DataBean> collect;
    private InfomationAdapter infomationAdapter;
    private int tag;
    private List<Information.DataBean> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().register(this);
        return inflater.inflate(R.layout.finformation_itemf, null);
    }
    @Subscribe(sticky = true)
    public void setdata(Shell shell){
        tag = shell.getTag();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inforv = (RecyclerView) view.findViewById(R.id.inforv);
        inforv.setLayoutManager(new LinearLayoutManager(getContext()));
        infomationAdapter = new InfomationAdapter();
        inforv.setAdapter(infomationAdapter);

        inforv.addOnItemTouchListener(new RecycleviewItemClickListener(getContext(), inforv, new RecycleviewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                EventBus.getDefault().postSticky(collect.get(postion));
//                startActivity(new Intent(FinformationItemfFragment.this,xx.class));
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        }));
    }

    private void initWait() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.await, null, false);
        builder.setView(inflate);
        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible){
            if (MTF==false){
            }else {
                alertDialog.dismiss();
            }
        }else {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onFragmentFirstVisible() {
        MTF = false;
        initWait();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                MTF = true;
                initdata();
                onFragmentVisibleChange(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Handler handler = new Handler();
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initdata() {
        data = gson.fromJson(informationjson, Information.class).getData();
        collect = data.stream().filter(dataBean -> dataBean.getStatus() == tag).collect(Collectors.toList());
        handler.post(() -> {
            infomationAdapter.setInformations(collect);
            infomationAdapter.notifyDataSetChanged();
            alertDialog.dismiss();
        });
    }


    String informationjson = "\n" +
            "   {\n" +
            "       \"status\": 200,\n" +
            "       \"message\": \"SUCCESS\",\n" +
            "       \"data\": [\n" +
            "           {\n" +
            "               \"id\": 44,\n" +
            "               \"informationName\": \"测试资源中心\",\n" +
            "               \"time\": 1579829400,\n" +
            "               \"words\": \"上一篇文章已经对补间动画做了详细的说明，不过这里还是需要重复说一下动画属性的作用。\",\n" +
            "               \"video\": \"/factory_uploads2020/01/11/20200111041732135.mp4\",\n" +
            "               \"icon\": \"/factory_uploads2020/01/11/20200111041342793.png\",\n" +
            "               \"status\": 0\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 4,\n" +
            "               \"informationName\": \"轩逸·纯电电池生产线探秘 安全是关键\",\n" +
            "               \"time\": 1571968140,\n" +
            "               \"words\": \"新能源汽车是近年来的发展趋势所在，从消费者的将信将疑到被广泛认可，也证明了新能源汽车的发展速度。当然，近来新能源汽车事故频发，安全问题再次成为消费者的关注焦点。采用与聆风同源的E-Platform电动车平台的轩逸·纯电，其电池诞生于日产全球第四条全自动电池生产线，而我们今天有幸能够来到花都参观，我们也来深入探秘它究竟有何过人之处，如何保证用户的安全。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 6,\n" +
            "               \"informationName\": \"占核心地段 观上汽技术伦敦前瞻工作室\",\n" +
            "               \"time\": 1572143400,\n" +
            "               \"words\": \"随着中国汽车市场的逐渐饱和以及自主品牌的日渐成熟，“走出去”也成为了众多车企保持高速增长的共识。在上汽集团新四化的战略中，“全球化”也是其向前向上发展的重要一步。于是着眼于全球市场的产品，就被要求拥有更高的技术指标和符合国际前沿的设计理念，在上汽设计伯明翰前瞻工作室之后，上汽设计在位于伦敦核心区域的前瞻设计工作室正在发挥着越来越重要的作用。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 7,\n" +
            "               \"informationName\": \"广汽新能源工厂竣工 首款车型或5月上市\",\n" +
            "               \"time\": 1572222300,\n" +
            "               \"words\": \"2018年12月23日，位于广州番禺区的广汽新能源智能生态工厂正式举办竣工仪式。新工厂包含于广汽智联新能源汽车产业园中，该园规划面积5万km²，主要涵盖智能制造区、创客服务区和智能汽车小镇三大板块。总规划产能40万辆/年，分两期建设，本次竣工的为一期建设的工厂，产能为20万辆/年，占地47万㎡，总投资47亿元，于2017年9月开工建设。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 0\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 8,\n" +
            "               \"informationName\": \"敏安汽车工厂竣工 电动SUV“澜图”亮相\",\n" +
            "               \"time\": 1571968260,\n" +
            "               \"words\": \"11月2日，敏安汽车工厂竣工仪式暨全球合作伙伴大会在淮安基地圆满完成。敏实集团董事局主席、敏安汽车董事长秦荣华、敏安汽车总经理张学平等企业高层以及200余家供应商代表出席了本次活动。活动在敏安汽车工厂总装车间内举行，与会嘉宾共同见证了敏安汽车工厂的正式竣工。与此同时，敏安汽车内部代号是“A2001”的首款A+级电动SUV正式亮相，随着新车一同亮相的还有全新的车型品牌名称和标识。据介绍，敏安此次亮相的车型品牌命名为“澜图”，使用全新的品牌标识\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 9,\n" +
            "               \"informationName\": \"安亭工厂动工 国产大众纯电车来期将至\",\n" +
            "               \"time\": 1571367300,\n" +
            "               \"words\": \"2018年10月19日，位于上海安亭的大众新能源工厂正式动工，该工厂是大众汽车全球首个专为MEB新能源平台设计建造的工厂，工厂占地面积40.56万㎡，总投入170亿元人民币，规划产能为年产30万辆，工厂预计在2020年10月投产。工厂大规模使用AGV小车，采用RFID人工智能识别技术、用于领先质量控制的光学测量技术、机器人视觉系统、人-机器人协作技术、无纸化工厂理念、能源管理系统等职能制造手段，并利用AR/VR技术对员工进行培训和认证。\\r\\n\\r\\n　　安亭的新能源工厂采用27项环保、节能技术，实现在能源、水、二氧化碳、挥发性有机物和废弃物等5项关键环境指标下降20%，成为上汽大众现代化绿色标杆工厂。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 10,\n" +
            "               \"informationName\": \"自主研发先锋 东风风神1.0T发动机揭秘\",\n" +
            "               \"time\": 1571363700,\n" +
            "               \"words\": \"不知道从什么时候起，中国自主研发的发动机开启了崭新的篇章。从过去依托国外先进技术、到如今完全独立开发世界一流发动机，国产发动机的自主研发之路可谓是一路高歌。借着这次参观东风风神发动机工厂的机会，我们也有幸接触到了东风风神全新的1.0T三缸发动机。东风风神的这款1.0T发动机已经完成了SOP（量产签署），现已在东风风神AX4等车型上进行了试装，届时东风风神AX4将会为消费者提供更多样的动力选择，而这款已经达到国六排放标准的1.0T发动机毫无疑问也将增强东风风神车型的竞争力。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 11,\n" +
            "               \"informationName\": \"用匠心诠释东方豪华 雷克萨斯九州工厂\",\n" +
            "               \"time\": 1571968320,\n" +
            "               \"words\": \"匠，只有6笔的会意字，意味却很深远——盛放工具的筐里放着斧头等工具，本意是从事木工，现在常指在某一方面具备纯熟技能的能工巧匠。匠人们的心思就是“匠心”。我们今天就要探寻何为匠心。我理解的匠心应该包含以下三点：必须全身心投入；对所做的事有执念；做出高水平的成果。这样的生产过程才能被称为“匠心制造”。而一家坐落在东方的豪华汽车品牌，正好诠释了什么叫工匠精神，它就是雷克萨斯。话不多说，现在请跟我走进雷克萨斯九州工厂，开启一段匠心之旅。\\r\\n\\r\\n　　之所以选择九州工厂，是因为这里自2000年起，已五次获得J.D. Power全球工厂品质“白金大奖”（Platinum Plant Quality Award，2000/2001/2011/2016年/2017年）。世界上各工厂中五次获得该奖项的仅有本工厂和丰田汽车田原工厂，因此这里绝对是雷克萨斯用“匠心”诠释东方豪华的典范。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 12,\n" +
            "               \"informationName\": \"C-NCAP五星的来源 访神龙公司成都工厂\",\n" +
            "               \"time\": 1572125700,\n" +
            "               \"words\": \"4月21日，中国汽车技术研究中心在济南发布了2018年度C-NCAP第一批评价结果，东风雪铁龙天逸C5 AIRCROSS以56.8分荣膺五星安全评价，我们也在当时对成绩结果做出了评价。PSA集团在国内拥有两家合资工厂，一家位于深圳的长安标致雪铁龙汽车主要生产DS品牌系列车型， 另一家合资企业神龙公司目前有四个生产基地，其中三个位于湖北，一个在四川成都。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 13,\n" +
            "               \"informationName\": \"幸福怎样炼成的 探访江淮瑞风R3的秘密\",\n" +
            "               \"time\": 1572200700,\n" +
            "               \"words\": \"江淮瑞风R3正式上市，这辆江淮汽车旗下首款家用MPV自海南车展预售之初就获得了巨大的关注，但瑞风R3除了沿袭老牌MPV瑞风的产品DNA外，还有一个特点被大家所忽略，那就是瑞风R3是江淮全面接轨大众标准打造的模范车型。那么，这款江淮瑞风R3到底有何过人之处呢？借上市之机，我们也参观了位于江淮汽车第二工厂的总装车间及测试实验室，深入探访瑞风R3背后的秘密江淮乘用车第二工厂始建于2010年，总产能规划60万辆，工厂早先以生产宾悦、瑞鹰为主，在江淮汽车与大众汽车深入合作后，江淮汽车要求除了江淮大众的产品要达到大众标准外，江淮汽车的燃油车也要达到大众的标准，于是江淮汽车对生产线进行大规模升级。\",\n" +
            "               \"video\": \"/factory_uploads2020/01/11/20200111030906905.mp4\",\n" +
            "               \"icon\": \"/factory_uploads2020/01/11/20200111030906427.png\",\n" +
            "               \"status\": 3\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 14,\n" +
            "               \"informationName\": \"制造未来 参观上汽通用武汉发动机工厂\",\n" +
            "               \"time\": 1572398700,\n" +
            "               \"words\": \"当你用上iPhone X、PS VR等最新的电子产品，你是否会感觉自己站在科技的前沿？这些产品确实代表着消费电子的尖端，但是就整个科技产业来说，它们还算不上前沿。最新的科技往往会率先应用到军事领域，然后是生产制造领域。比如我们熟悉的互联网和不太熟悉的无人驾驶，其实最早都是美国国防高级研究计划局（DARPA）发起的项目。\\r\\n\\r\\n　　回到汽车领域，今天汽车产业的三大趋势是智能互联、无人驾驶、新能源，这些都是各个大国的军方研究过的技术。甚至新一代的汽车工厂，都要比汽车本身更加接近这三大趋势。不信？那就随我看看上汽通用最先进的发动机工厂吧。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 15,\n" +
            "               \"informationName\": \"神车如何炼就？ 讴歌NSX生产过程全揭秘\",\n" +
            "               \"time\": 1572485100,\n" +
            "               \"words\": \"可能你看过各类汽车工厂的探访文章，看腻了千篇一律的冲压、焊接、喷漆、总装……当你看到这篇文章标题的时候，是否有想直接关掉窗口的冲动？别着急，我们今天给你看一些不一样的：制造一台NSX这样可以载入史册的超级跑车，同制造一台高尔夫的过程可是天壤之别。作为传奇跑车NSX的继任者，讴歌全新一代NSX承载了太多车迷的期待——要知道即便在今天，在性能、可靠性和易用性上全面超越前作，仍然是一件十分挑战的事情。那么如何让全新一代NSX在品质上延续讴歌的优秀口碑，并且能够配得上NSX这个如丰碑一般的标识？讴歌的做法是，集结了讴歌甚至本田在美国最为出色的工程师，并且单独为NSX打造一座专用的工厂。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 3\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 16,\n" +
            "               \"informationName\": \"大众第八代高尔夫正式发布 全方位升级\",\n" +
            "               \"time\": 1571799000,\n" +
            "               \"words\": \"德国当地时间10月24日，大众第八代高尔夫在沃尔夫斯堡正式发布。到第八代车型推出，高尔夫已经走过了45年的征程，至今累计全球销售超过3500万辆，长期位列全世界最热销车型前三名。第八代高尔夫采用最新的家族式设计，基于大众MQB平台的最新版本打造，动力提供汽油、柴油、插电式混动系统多种选择，当然未来还将推出我们非常熟悉的GTI和R等高性能版本。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 17,\n" +
            "               \"informationName\": \"丰田全新RAV4荣放于今晚上市 内外革新\",\n" +
            "               \"time\": 1572331500,\n" +
            "               \"words\": \"2019年10月25日，一汽丰田全新RAV4荣放将于19:00公布售价。作为家族中的第五代车型，基于TNGA架构GA-K平台打造的全新RAV4荣放，在采用新家族式设计语言的同时，提供2.0L燃油版及2.5L双擎版以供消费者选择，并配备了三种四驱系统，此前官方公布的部分车型预售价格为20.00-24.50万元。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 18,\n" +
            "               \"informationName\": \"东风启辰e30正式上市 补贴后售6.18万起\",\n" +
            "               \"time\": 1572324300,\n" +
            "               \"words\": \"2019年10月22日，东风启辰小型纯电动SUV——启辰e30正式上市，补贴后售价区间为6.18-7.48万元。东风启辰e30续航里程将达到271km，在外观方面采用了东风启辰的家族式设计，并且车身多处都有蓝色元素进行点缀，进一步彰显出了新能源的身份。此外，启辰T60EV车型也将在年内推出。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 19,\n" +
            "               \"informationName\": \"2019东京车展： 全新本田飞度静态评测\",\n" +
            "               \"time\": 1571968620,\n" +
            "               \"words\": \"2019年东京车展上，全新本田飞度正式发布。作为换代车型，全新飞度采用了最新的设计语言，内饰也经过了重新设计，并且采用了全新的动力总成，整体风格和现款车型截然不同。全新飞度在外观上的的转变还是非常之大的，整体线条圆润了很多，很难看到上一代车型的影子。但毫无疑问的是，新车设计明显现代化、城市化了很多。车头中间采用了镂空的设计，两侧大灯连接到了一起，中间部分的设计和现款思域的设计很像，也是本田当前家族化的设计元素。\",\n" +
            "               \"video\": null,\n" +
            "               \"icon\": null,\n" +
            "               \"status\": 2\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 20,\n" +
            "               \"informationName\": \"19款丰田霸道2700特惠原装进口环保公开\",\n" +
            "               \"time\": 1571972940,\n" +
            "               \"words\": \"19款丰田霸道2700是丰田旗下的一款大型SUV，丰田霸道凭仗其良好的越野机能和优良的外观计划和其超高的性价比为其在竞争如斯剧烈的越野车市场夺得冠军，车型如同名字一样，非常的霸道。丰田霸道2700中东版纯正越野利器换了一副全新的面孔重新示人。位于保险杠两侧的圆形雾灯更加的简洁、直观，拥有那仿佛钢铁侠一样的夺人的眼神。全身硬朗的棱线设计更是振奋人心，体现对完美的追求。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025110933256.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025110933363.jpg\",\n" +
            "               \"status\": 0\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 21,\n" +
            "               \"informationName\": \"2019款三菱帕杰罗v97 金标顶配中东版现车\",\n" +
            "               \"time\": 1571877000,\n" +
            "               \"words\": \"三菱帕杰罗V97中东版3.8L排量，进口3菱帕杰罗V97的越野性能，属于实用派一类。没有过多的噱头，在恶劣条件下的优良的可靠性能带你摆脱任何困境。三菱帕杰罗V97搭载V63.8L发动机 动力方面明显提升 而且在40万之内硬派越野车型里面 别无对手.传统硬派越野车的前脸造型设计，同样出现在了它的前部，3菱车标是身份的象征。V624气门MIVEC 动力优化系统，处处都体现出它的力量与科技，凝视的双眼仿佛一直都在注视着前方的艰难险阻。激情、运动、宽适……这就是在汽车领域内让酷爱玩车一族羡慕的SUV车型。沉稳越野外观加上超选四驱系统 绝对给力.现车惠降，车身颜色齐全，三菱帕杰罗V97现车充足 喜欢的朋友买车不用愁 要是论40万之内的硬派越野车 那么非帕杰罗不可.相信专业的力量。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025111034571.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025111034137.jpg\",\n" +
            "               \"status\": 0\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 22,\n" +
            "               \"informationName\": \"补贴后售10.38-11.98万元比亚迪e3正式上市\",\n" +
            "               \"time\": 1572030600,\n" +
            "               \"words\": \"10月24日，比亚迪旗下全新紧凑型车——比亚迪e3正式上市。新车共推出3款车型，补贴后售价区间为10.38-11.98万元，其可看做是比亚迪e2的三厢版车型。 外观方面，新车依旧采用了“Dragon Face”设计元素，前脸六边形进气格栅内部采用了点阵式设计，配合两侧锐利的大灯组以及下方两侧L型进气孔造型，辨识度非常高。车身侧面，凌厉的腰线配合红色刹车卡钳，营造出不错的运动氛围。车尾部，尾灯组采用了贯穿式设计。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025111252135.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025111252179.jpg\",\n" +
            "               \"status\": 0\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 23,\n" +
            "               \"informationName\": \"比亚迪e3补贴后10.38万元起售！两种续航里程！\",\n" +
            "               \"time\": 1572442200,\n" +
            "               \"words\": \"从现如今看来，发展新能源车是汽车行业发展的大趋势，所以想要在未来站得住脚，布局新能源车领域至关重要。在国内新能源车市场混得风生水起的比亚迪显然是有这种觉悟的，这不，比亚迪e2才上市一个多月，比亚迪就又带来了它的“亲兄弟”——比亚迪e3。比亚迪e3共推出3款车型，综合补贴后售价为10.38-11.98万元，将归属比亚迪e网进行销售。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025111416488.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025111416184.jpg\",\n" +
            "               \"status\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 24,\n" +
            "               \"informationName\": \"第8代高尔夫正式发布，有哪些改变？\",\n" +
            "               \"time\": 1571973240,\n" +
            "               \"words\": \"今天整个汽车圈被刷屏了，原因很简单，就是第8代高尔夫正式发布了官方图片。高尔夫是大众汽车最畅销的车型。并且在他的版本上还开发出了纯电动的车型，以及旅行版，高性能版GTI，在全世界拥有非常多的粉丝。第8代车型在曝光之前，网络上就已经在疯传他的设计图，而今天正式曝光之后，其实与之前的设计图相差并不是非常大，全新的造型设计增加了不少棱角，让前脸看上去更加的犀利，更加的霸气和运动。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025111525830.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025111525713.jpg\",\n" +
            "               \"status\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 25,\n" +
            "               \"informationName\": \"普拉多兄弟 比帕杰罗硬派 4.0L+全时四驱\",\n" +
            "               \"time\": 1572484200,\n" +
            "               \"words\": \"丰田普拉多是越野实力派的代表，被国内消费者誉为\\\"进藏神器\\\"。这款车型在国内市场一直很受欢迎，质量稳定性，和耐用性被广大自驾游车主作为首选车型。其实，对于喜欢硬派SUV的消费者来说，还有一款SUV值得重点关注，它跟普拉多是兄弟车型，但在外观上，它比帕杰罗更为硬派。它就是丰田旗下的4Runner，这款车型在国内市场称为\\\"超霸”，越野车中的“纯爷们”。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025111609897.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025111609961.jpg\",\n" +
            "               \"status\": 1\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 26,\n" +
            "               \"informationName\": \"新车实拍：别克7座中大型SUV昂科旗首次亮相\",\n" +
            "               \"time\": 1571903400,\n" +
            "               \"words\": \"2019年10月22日，上汽通用汽车在北京举办品鉴会，首次公开展示了别克品牌7座中大型SUV——昂科旗，并邀请泛亚汽车中心的专家，为来宾介绍了该车设计理念。据了解，昂科旗有望在今年年底之前上市销售。　别克品牌的SUV家族，由昂科拉（小型）、昂科拉GX（紧凑型）和昂科威（中型）构成。更高一级的中大型SUV昂科雷，始终以进口车的形式销售。目前，中大型SUV在我国处于高高在上的局面，比如奥迪Q7、宝马X5与奔驰GLE，价格高达六七十万元。大众推出的途昂与马自达推出的CX-8，首次把售价拉进30万元以内，但后者并未获得市场认可。在这种情况下，昂科旗的诞生，不仅在于别克SUV家族品种从此齐全，更是令消费者从此拥有更多选择。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025111709998.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025111709671.jpg\",\n" +
            "               \"status\": 2\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 27,\n" +
            "               \"informationName\": \"极星2（Polestar2）首发版售价公布 售价41.8万元\",\n" +
            "               \"time\": 1572167700,\n" +
            "               \"words\": \"今日，Polestar中国正式宣布品牌旗下首款纯电动车型极星2（Polestar 2）首发版最终统一零售价格为人民币418,000元，该价格将于2019年10月22日生效，已经支付订金的客户在最终交付车辆时将会按照该最终零售价执行。此次极星2在中国地区的价格调整是基于极星全球的定价策略协同联动统一进行调整后的结果。最终统一零售价的公布无疑将使极星2在中国市场更具竞争力\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025111757257.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025111757919.jpg\",\n" +
            "               \"status\": 2\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 28,\n" +
            "               \"informationName\": \"在滇西高原感受东南DX5 颜值品质驾控“三高”\",\n" +
            "               \"time\": 1572223500,\n" +
            "               \"words\": \"东南汽车在SUV方面，已有DX3与DX7，前不久，又推出DX5。这是一款小型SUV。以级别而论，与DX3相同。那么，厂家为何推出2款同级车？东南DX5的主题是什么？带着这个问题，我在滇西北高原，实地感受了这款新车。对于东南汽车，我的记忆还停留在菱帅、蓝瑟等车型。其中最深的印象是动力与操控非常给力，外观与内饰普普通通。这种特色在10多年前没毛病，但在今天就悬了。如今许多人选车时，把相貌放在首位。第一眼没看上的话，后面……根本没后面了。就此打住。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025111934576.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025111934935.jpg\",\n" +
            "               \"status\": 2\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 29,\n" +
            "               \"informationName\": \"本田插电混动车型即将进入我国 比纯电更具优势\",\n" +
            "               \"time\": 1572146700,\n" +
            "               \"words\": \"本田混合动力技术当中的i-MMD，目前在我国，已经应用于雅阁、思威、奥德赛、艾力绅等车型中。在此基础上，本田公司研发的插电式混动系统——“SPORT HYBRID e＋”，将于2020年进入我国。具体车型虽然尚未公布，但它能在日常行驶中基本实现纯电动化，享受新能源政策的同时，拥有与燃油车相同的续航里程，所以说，这是一项比纯电更有现实意义的新能源技术。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025112033972.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025112033904.jpg\",\n" +
            "               \"status\": 3\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 30,\n" +
            "               \"informationName\": \"实地体验全新别克威朗 从里到外 焕然一新\",\n" +
            "               \"time\": 1572166500,\n" +
            "               \"words\": \"2019年9月4日，改款之后的别克威朗上市。在此之前，我曾在厂家举办的设计说明会上，观赏过这款车。这次有缘来到浙江奉化溪口，在青山秀水之间，亲身感受了一下这款刚刚上市的新车。与之前车型相比，新车在外观、动力、车联技术3个方面，都给人一种焕然一新的感觉。上汽通用旗下别克品牌的紧凑型轿车，有威朗、英朗与凯越，由此划分出高、中、低三个档次，价格从18万元下探到8万元。类似做法，在德系车阵营当中，同样存在。比如，上汽大众旗下有凌度、朗逸和桑塔纳，一汽-大众旗下有速腾、宝来和捷达，都是高、中、低三档。\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025112123128.mp4\",\n" +
            "               \"icon\": \"/factory_uploads/2019/10/25/20191025112123126.jpg\",\n" +
            "               \"status\": 3\n" +
            "           },\n" +
            "           {\n" +
            "               \"id\": 31,\n" +
            "               \"informationName\": \"绝不仅仅是油改电 试驾广汽本田纯电动车型VE-1\",\n" +
            "               \"time\": 1572075000,\n" +
            "               \"words\": \"随着国内新能源发展的步伐越走越快，越来越多的企业，加入了中国新能源汽车发展的大军。特别是到9102年，笔者注意到，一直态度表现暧昧的合资车企，也开始布局自身的新能源产品。日系企业，在新能源技术研究与应用方面，一直走在世界的前列。而作为其中对于技术和驾驶乐趣的“偏执狂”日本本田汽车，也将与近期在华推出了首款兼具驾驶乐趣的新能源汽车-广汽本田VE -1。笔者有幸在这款车型，上市之前短暂的体验了一下。那么这款车型是否很好的展现了运动与新能源汽车的和谐并存呢？\",\n" +
            "               \"video\": \"/factory_uploads/2019/10/25/20191025112249595.mp4\",\n" +
            "               \"icon\": \"/factory_uploads2020/01/11/20200111105219486.png\",\n" +
            "               \"status\": 3\n" +
            "           }\n" +
            "       ]\n" +
            "   }";
}