package com.amazon.android.l;

import com.amazon.android.d.a;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.amazon.android.l:
//			c, a

public abstract class TaskWorkflow_b extends c implements
        com.amazon.android.framework.resource.b {

    private static final KiwiLogger a = new KiwiLogger("TaskWorkflow");
    private com.amazon.android.framework.resource.ResourceManager_a b;
    private List c;
    private AtomicBoolean d;

    public TaskWorkflow_b() {
        c = new ArrayList();
        d = new AtomicBoolean(false);
    }

    protected void a() {
    }

    protected final void a(Task task) {
        com.amazon.android.d.a.a(task, "task");
        c.add(task);
        if (task instanceof com.amazon.android.l.a)
            ((com.amazon.android.l.a) task).setWorkflow(this);
    }

    protected abstract String b();

    public final void c() {
        d.set(true);
    }

    public final void execute() {
        if (KiwiLogger.TRACE_ON)
            a.trace((new StringBuilder()).append("Exiting task workflow: ")
                    .append(this).toString());

        try {
            Iterator iterator = c.iterator();
            while (iterator.hasNext()) {
                Task task = (Task) iterator.next();
                if (!d.get()) {
                    break;
                }
                task.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (KiwiLogger.TRACE_ON)
            a.trace("Finished set, exiting task workflow early");
        a();
    }

    public final void onResourcesPopulated() {
        Task task;
        for (Iterator iterator = c.iterator(); iterator.hasNext(); b.b(task))
            task = (Task) iterator.next();

    }

    public final String toString() {
        return b();
    }

}
