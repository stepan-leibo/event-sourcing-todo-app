import React, {Component} from 'react'
import Project from "./Project";

class AllTodos extends Component {
    render() {
        return (
            <Project isEnabled projectName="" title="All todos" todoFilter={(project, todos) => todos}/>
        )
    }
}

export default AllTodos