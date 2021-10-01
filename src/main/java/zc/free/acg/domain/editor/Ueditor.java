package zc.free.acg.domain.editor;

/**
 * @auther ZhengCong
 * @date 2020/5/7 23:47
 * 富文本编辑器使用
 */
public class Ueditor {
    private  String state;
    private  String url;
    private  String title;
    private  String original;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
