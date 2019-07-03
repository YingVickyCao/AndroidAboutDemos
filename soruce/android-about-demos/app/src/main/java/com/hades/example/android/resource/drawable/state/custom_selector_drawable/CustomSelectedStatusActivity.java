package com.hades.example.android.resource.drawable.state.custom_selector_drawable;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * http://charlesharley.com/2012/programming/custom-drawable-states-in-android
 */
public class CustomSelectedStatusActivity extends ListActivity {

    /* Copyright 2012 Charles Harley
     *
     * Licensed under the Apache License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at
     *
     *     http://www.apache.org/licenses/LICENSE-2.0
     *
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ExampleListAdapter());
    }

    private static class ExampleListAdapter extends BaseAdapter {

        // TODO: 2019/3/15
        private Message[] messages;
        private ExampleListAdapter() {
            messages = new Message[]{
                    new Message("Gas bill overdue", true),
                    new Message("Congratulations, you've won!", true),
                    new Message("Please reply!", false),
                    new Message("You ignoring me?", false),
                    new Message("Not heard from you", false),
                    new Message("Electricity bill", true),
                    new Message("Gas bill", true),
                    new Message("Holiday plans", false),
                    new Message("Marketing stuff", false),
            };
        }

        @Override
        public int getCount() {
            return messages.length;
        }

        @Override
        public Object getItem(int position) {
            return messages[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            MessageListItemView messageListItemView = (MessageListItemView) convertView;
            if (messageListItemView == null) {
                messageListItemView = new MessageListItemView(viewGroup.getContext());
            }
            Message message = (Message) getItem(position);
            messageListItemView.setMessageSubject(message.subject);
            messageListItemView.setMessageUnread(message.unread);
            return messageListItemView;
        }

    }

    private static class Message {

        private String subject;
        private boolean unread;

        private Message(String subject, boolean unread) {
            this.subject = subject;
            this.unread = unread;
        }

    }

}
