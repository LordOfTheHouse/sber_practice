import {UserCard} from "../components/UserCard";
import rose from "../img/rose2.jpg"

export const LogoutPage = () => {
    return (<div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', backgroundImage: `url(${rose})`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat' }}>
        <UserCard/>
    </div>);
}