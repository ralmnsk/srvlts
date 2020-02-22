package com.facultative.web.command.pagination;

import com.facultative.model.Mark;
import com.facultative.service.IMarkService;
import com.facultative.service.MarkServiceImpl;

public class ItemCounterTutorMarks implements IItemCounter {
    @Override
    public int getItemCount(long userId) {
        IMarkService<Mark> service = MarkServiceImpl.getInstance();
        return service.getCountMarksByTutorId(userId);
    }
}
