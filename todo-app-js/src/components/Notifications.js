import React, {Component} from 'react'
import { toast } from 'react-toastify';
import {connect} from "react-redux";
import {todoActionCreators} from "../actions";

class Notifications extends Component {

    ws;

    componentDidMount() {
        this.ws = new WebSocket("ws://localhost:6901/socket/notifications");
        this.ws.addEventListener(
            'message',
            ({data}) => {
                let todo = JSON.parse(data);
                toast.info(`Todo "${todo.text}" created.`);
                this.props.dispatch(todoActionCreators.todo.addOne(todo));
            }
        );
    }

    componentWillUnmount() {
        this.ws.close();
    }

    render() {
        return (
            <div>
            </div>
        )
    }
}

export default connect()(Notifications)