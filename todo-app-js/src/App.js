import React, {Component} from 'react';
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.min.css';
import AllTodos from "./components/AllTodos";
import Projects from "./components/Projects";
import Sidebar from "./components/Sidebar";
import StatsChart from "./components/StatsChart";
import {connect} from "react-redux";
import TodoGenerator from "./components/TodoGenerator";
import Notifications from "./components/Notifications";
import Events from "./components/Events";

class App extends Component {
    state = {
        isSidebarEnable: false
    };

    render() {
        let mainPanelClass = this.state.isSidebarEnable ? 'main-panel' : 'main-panel main-panel-fullscreen';
        return (
            <div className="App">
                <div className="navbar navbar-default">
                    <div className="container-fluid">
                        <div className="navbar-collapse collapse">
                            <ul className="nav navbar-nav">
                                <li>
                                    <a>
                                        <span
                                            className="glyphicon glyphicon-menu-hamburger menu-icon"
                                            onClick={() => this.setState({isSidebarEnable: !this.state.isSidebarEnable})}/>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                {this.state.isSidebarEnable && (<Sidebar/>)}
                <div className={mainPanelClass}>
                    <div className="content">
                        <div className="container-fluid">

                            <div className="row">
                                {this.props.isAllTodosEnabled && (
                                    <div className="col-md-6">
                                        <AllTodos/>
                                    </div>
                                )}
                                {this.props.isEventsEnabled && (
                                    <div className="col-md-6">
                                        <Events/>
                                    </div>
                                )}
                                {this.props.isProjectEnabled && (
                                    <div className="col-md-6">
                                        <Projects/>
                                    </div>
                                )}
                                {this.props.isStatsEnabled && (
                                    <div className="col-md-6">
                                        <StatsChart/>
                                    </div>
                                )}
                                {this.props.isTodoAutoGenEnabled && (
                                    <TodoGenerator/>
                                )}
                                {this.props.isNotificationsEnabled && (
                                    <Notifications/>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
                <ToastContainer
                    position="bottom-right"
                    type="default"
                    autoClose={5000}
                    hideProgressBar
                    newestOnTop
                    closeOnClick
                    pauseOnHover
                />
            </div>
        );
    }
}

function mapStateToProps({admin}) {
    return admin;
}

export default connect(mapStateToProps)(App);
