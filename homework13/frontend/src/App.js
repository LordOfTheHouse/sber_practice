
import './App.css';
import { Layout } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import { Link, Route, Routes } from "react-router-dom";
import { AssortmentPage } from "./page/AssortmentPage";
import { CartPage } from "./page/CartPage";
import { LogoutPage } from "./page/LogoutPage";
import { NotFoundPage } from "./page/NotFoundPage";

function App() {
    return (
        <Layout className="layout" style={{ padding: '0 50px' }}>
            <Header style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'space-between',
                background: '#183158',
                color: 'white',
            }}>
                <div style={{ padding: '0 10px', flex: 1 }}>
                    <h1 style={{ margin: 0, fontSize: '36px', textShadow: '2px 2px 4px #000000' }}>Свободный рынок</h1>
                </div>
                <div style={{ textAlign: 'right' }}>
                    <div style={{ padding: '0 10px', display: 'inline-block' }}>
                        <Link to="/">Главная</Link>
                    </div>
                    <div style={{ padding: '0 10px', display: 'inline-block' }}>
                        <Link to="/cartUser">Корзина</Link>
                    </div>
                    <div style={{ padding: '0 10px', display: 'inline-block' }}>
                        <Link to="/logout">Пользователь</Link>
                    </div>
                </div>
            </Header>

            <Content>
                <Routes>
                    <Route index element={<AssortmentPage />} />
                    <Route path="/cartUser" element={<CartPage />} />
                    <Route path="/logout" element={<LogoutPage />} />
                    <Route path="*" element={<NotFoundPage />} />
                </Routes>
            </Content>
        </Layout>
    );
}

export default App;