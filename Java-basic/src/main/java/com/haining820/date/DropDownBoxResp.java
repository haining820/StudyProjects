package com.haining820.date;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-12
 * Time: 11:12
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DropDownBoxResp {

    private String label;

    private String dropdownText;

    private String btnText;

    private String jumpUrl;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDropdownText() {
        return dropdownText;
    }

    public void setDropdownText(String dropdownText) {
        this.dropdownText = dropdownText;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
