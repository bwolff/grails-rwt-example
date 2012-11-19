package grails.rwt.example

import org.eclipse.jface.dialogs.MessageDialog
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.jface.viewers.ListViewer
import org.eclipse.rap.rwt.lifecycle.IEntryPoint
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.List
import org.eclipse.swt.widgets.Shell

// "Note the use of the Groovy syntax"

class HelloEntryPointService implements IEntryPoint { // "The service needs to implement IEntryPoint"

    def fooService // "Other services will get injected by Grails"

    @Override
    int createUI() {
        log.info("Creating RWT UI ...") // "Implicit logger object"

        Display display = new Display()
        Shell shell = new Shell(display)
        shell.setSize(500, 400)
        shell.layout = new GridLayout()
        shell.setText("Hello RWT!")

        final Label label = new Label(shell, SWT.NONE)
        label.layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false)

        // "JFace is available"
        ListViewer viewer = new ListViewer(shell, SWT.V_SCROLL | SWT.H_SCROLL)
        viewer.list.layoutData = new GridData(SWT.FILL, SWT.FILL, true, true)
        viewer.contentProvider = ArrayContentProvider.instance
        viewer.labelProvider = new LabelProvider()
        viewer.input = Foo.list() // "Use domain objects and GORM, no DTOs required!"

        Button button = new Button(shell, SWT.PUSH)
        button.layoutData = new GridData(SWT.BEGINNING, SWT.CENTER, false, false)
        button.text = "Click me!"
        button.addSelectionListener(new SelectionAdapter() {
            Integer num = 0

            @Override
            public void widgetSelected(SelectionEvent e) {
                label.text = fooService.serviceMethod(num++) // "Call methods of other services"
            }
        })

        shell.open()
        return 0
    }
}
