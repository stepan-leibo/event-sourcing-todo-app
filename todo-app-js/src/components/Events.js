import React, {Component} from 'react'
import TodoApi from "../utils/TodoApi";

class Events extends Component {

    state = {
        events: []
    }

    componentDidMount() {
        TodoApi.fetchUrl("http://localhost:6901/events")
            .then(resp => resp.json())
            .then(data => {
                this.setState({events: data});
                console.log(data);
            })
    }

    render() {
        return (
            <div className="card">
                <div className="content">
                    <p className="event">
                        {JSON.stringify(this.state.events.slice(0,2), null, 2)}
                    </p>
                </div>
            </div>
        )
    }
}

export default Events