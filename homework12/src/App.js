import './App.css';
import {Layout} from "antd";
import {Content, Header} from "antd/es/layout/layout";
import {ListProduct} from "./components/ListProduct";
import {UserCard} from "./components/UserCard";
import {useSelector} from "react-redux";
import {useState} from "react";
import Cart from "./components/Cart";
import rose from "./img/rose2.jpg";

function App() {
    const user = useSelector((state) => state.user.user);
    const [isUser, setIsUser] = useState(false);
    const stepUser = () => {
        setIsUser(true)
    };
    const stepProductsList = () => {
        setIsUser(false)
    };
    return (
        <Layout className="layout" style={{padding: '0 50px'}}>
            <Header style={{display: 'flex', alignItems: 'center', justifyContent: 'space-between'}}>
                <h1 style={{color: "white"}} onClick={stepProductsList}>Список товаров</h1>
                <h1 style={{color: "white"}} onClick={stepUser}>{user.name}</h1>
            </Header>


            <Content >
                <div>
                    {isUser ? (
                        <div >
                            <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', backgroundImage: `url(${rose})`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat' }}>
                                <UserCard/>
                            </div>
                            <div>
                                <Cart/>
                            </div>
                        </div>
                    ) : (
                        <ListProduct/>

                    )}
                </div>
            </Content>
        </Layout>
    );
}

export default App;