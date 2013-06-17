/*******************************************************************************
 * Copyright 2012-2013 Benjamin Wolff
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Benjamin Wolff - initial API and implementation
 ******************************************************************************/
package grails.rwt.example

import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.jface.layout.GridLayoutFactory
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.jface.viewers.ListViewer
import org.eclipse.rap.rwt.application.EntryPoint
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell

// "Note the use of the Groovy syntax"

class HelloEntryPointService implements EntryPoint { // "The service needs to implement EntryPoint"

    def fooService // "Other services will get injected by Grails"

    @Override
    public int createUI() {
        log.info("Creating RWT UI ...") // "Implicit logger object"

        Display display = new Display()
        Shell shell = new Shell(display)
//        shell.setSize(500, 400)
        shell.setText("Hello RWT!")
        GridLayoutFactory.swtDefaults().applyTo(shell)

        final Label label = new Label(shell, SWT.NONE)
        GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(label)

        // "JFace is available"
        ListViewer viewer = new ListViewer(shell, SWT.V_SCROLL | SWT.H_SCROLL)
        GridDataFactory.swtDefaults().hint(500, 300).applyTo(viewer.list)
        viewer.contentProvider = ArrayContentProvider.instance
        viewer.labelProvider = new LabelProvider()
        viewer.input = Foo.list() // "Use domain objects and GORM, no DTOs required!"

        Button button = new Button(shell, SWT.PUSH)
        GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(button)
        button.text = "Click me!"
        button.addSelectionListener(new SelectionAdapter() {
            Integer num = 0

            @Override
            public void widgetSelected(SelectionEvent e) {
                label.text = fooService.serviceMethod(num++) // "Call methods of other services"
            }
        })

        shell.pack()
        shell.open()

        while(!shell.isDisposed()) {
            if(!display.readAndDispatch()) {
                display.sleep()
            }
        }
        display.dispose()

        return 0;
    }
}
