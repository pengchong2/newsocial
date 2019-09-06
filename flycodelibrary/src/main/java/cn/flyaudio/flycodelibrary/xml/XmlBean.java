package cn.flyaudio.flycodelibrary.xml;

/**
 * @author meihui
 */
public class XmlBean {

    private String item;
    private String background;
    private String classname;
    private String m_Text;
    private String packagename;
    private String tag;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getM_Text() {
        return m_Text;
    }

    public void setM_Text(String m_Text) {
        this.m_Text = m_Text;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "XmlBean{" +
                "item='" + item + '\'' +
                ", background='" + background + '\'' +
                ", classname='" + classname + '\'' +
                ", m_Text='" + m_Text + '\'' +
                ", packagename='" + packagename + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
