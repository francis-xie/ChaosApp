

package com.basic.code.fragment.components;

import com.basic.leaf.annotation.Page;
import com.basic.code.R;
import com.basic.code.base.ComponentContainerFragment;
import com.basic.code.fragment.components.pickerview.AddressPickerFragment;
import com.basic.code.fragment.components.pickerview.OptionsPickerViewFragment;
import com.basic.code.fragment.components.pickerview.RulerViewFragment;
import com.basic.code.fragment.components.pickerview.SeekBarFragment;
import com.basic.code.fragment.components.pickerview.TimePickerFragment;

/**

 * @since 2019/1/1 下午8:30
 */
@Page(name = "选择器", extra = R.drawable.ic_widget_picker_view)
public class PickerViewFragment extends ComponentContainerFragment {
    @Override
    protected Class[] getPagesClasses() {
        return new Class[] {
                TimePickerFragment.class,
                AddressPickerFragment.class,
                OptionsPickerViewFragment.class,
                RulerViewFragment.class,
                SeekBarFragment.class
        };
    }
}
