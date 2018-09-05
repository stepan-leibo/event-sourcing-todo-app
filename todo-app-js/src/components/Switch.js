import React, {Component} from 'react'
import PropTypes from 'prop-types';

class Switch extends Component {
    static propTypes = {
        onChange: PropTypes.func.isRequired,
        isOn: PropTypes.bool.isRequired
    };

    render() {
        let switchClass = `switch-animate ${this.props.isOn ? 'switch-on': 'switch-off'}`;
        return (
            <div className="switch has-switch" onClick={this.props.onChange}>
                <div className={switchClass}>
                    <span className="switch-left">ON</span>
                    <label>&nbsp;</label>
                    <span className="switch-right">OFF</span>
                </div>
            </div>
        )
    }
}

export default Switch