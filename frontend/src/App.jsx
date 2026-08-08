import { useEffect, useState } from "react";
import axios from "axios";
import "./index.css";

const API = "http://localhost:8080";

function App() {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [page, setPage] = useState("Dashboard");

  const login = (jwt) => {
    localStorage.setItem("token", jwt);
    setToken(jwt);
  };

  const logout = () => {
    localStorage.removeItem("token");
    setToken(null);
  };

  if (!token) {
    return <Login onLogin={login} />;
  }

  return (
    <Dashboard
      page={page}
      setPage={setPage}
      logout={logout}
      token={token}
    />
  );
}

/* ================= LOGIN ================= */

function Login({ onLogin }) {
  const [email, setEmail] = useState("vasanth@gmail.com");
  const [password, setPassword] = useState("admin123");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const response = await axios.post(`${API}/auth/login`, {
        email,
        password,
      });

      const data = response.data;

      const jwt =
        data.token ||
        data.accessToken ||
        data.data?.token ||
        data.data?.accessToken;

      if (!jwt) {
        setError("Login successful, but JWT token was not found.");
        return;
      }

      onLogin(jwt);
    } catch (err) {
      setError(
        err.response?.data?.message ||
          "Login failed. Check email and password."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">
      <div className="login-card">
        <div className="logo">🎓</div>

        <h1>Smart Campus</h1>
        <p className="subtitle">Management System</p>

        <form onSubmit={handleLogin}>
          <label>Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Enter email"
            required
          />

          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter password"
            required
          />

          {error && <div className="error">{error}</div>}

          <button className="login-button" disabled={loading}>
            {loading ? "Logging in..." : "Login"}
          </button>
        </form>
      </div>
    </div>
  );
}

/* ================= DASHBOARD ================= */

function Dashboard({ page, setPage, logout, token }) {
  const menu = [
    "Dashboard",
    "Students",
    "Faculty",
    "Courses",
    "Attendance",
    "Results",
    "Notices",
  ];

  return (
    <div className="app">
      <aside className="sidebar">
        <div className="brand">
          <span>🎓</span>
          <div>
            <strong>Smart Campus</strong>
            <small>Management System</small>
          </div>
        </div>

        <nav>
          {menu.map((item) => (
            <button
              key={item}
              className={page === item ? "active" : ""}
              onClick={() => setPage(item)}
            >
              {getIcon(item)}
              {item}
            </button>
          ))}
        </nav>

        <button className="logout" onClick={logout}>
          🚪 Logout
        </button>
      </aside>

      <main className="main">
        <header>
          <div>
            <h2>{page}</h2>
            <p>Smart Campus Management System</p>
          </div>

          <div className="admin">
            👤 <span>Admin</span>
          </div>
        </header>

        {page === "Dashboard" && <Home token={token} />}
        {page === "Students" && (
          <Module title="Students" endpoint="/students" token={token} />
        )}
        {page === "Faculty" && (
          <Module title="Faculty" endpoint="/faculty" token={token} />
        )}
        {page === "Courses" && (
          <Module title="Courses" endpoint="/courses" token={token} />
        )}
        {page === "Attendance" && (
          <Module title="Attendance" endpoint="/attendance" token={token} />
        )}
        {page === "Results" && (
          <Module title="Results" endpoint="/results" token={token} />
        )}
        {page === "Notices" && (
          <Module title="Notices" endpoint="/notices" token={token} />
        )}
      </main>
    </div>
  );
}

/* ================= HOME ================= */

function Home({ token }) {
  const [counts, setCounts] = useState({
    students: 0,
    faculty: 0,
    courses: 0,
    attendance: 0,
  });

  useEffect(() => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };

    Promise.all([
      axios.get(`${API}/students?page=0&size=100`, { headers }),
      axios.get(`${API}/faculty`, { headers }),
      axios.get(`${API}/courses`, { headers }),
      axios.get(`${API}/attendance`, { headers }),
    ])
      .then(([students, faculty, courses, attendance]) => {
        setCounts({
          students: getArray(students.data).length,
          faculty: getArray(faculty.data).length,
          courses: getArray(courses.data).length,
          attendance: getArray(attendance.data).length,
        });
      })
      .catch(() => {});
  }, [token]);

  return (
    <>
      <div className="welcome">
        <div>
          <h1>Welcome to Smart Campus 👋</h1>
          <p>Manage your campus activities from one place.</p>
        </div>
      </div>

      <div className="cards">
        <StatCard icon="🎓" title="Students" value={counts.students} />
        <StatCard icon="👨‍🏫" title="Faculty" value={counts.faculty} />
        <StatCard icon="📚" title="Courses" value={counts.courses} />
        <StatCard icon="📊" title="Attendance Records" value={counts.attendance} />
      </div>

      <div className="dashboard-info">
        <div className="info-card">
          <h3>Quick Access</h3>
          <p>Use the sidebar to manage students, faculty, courses and academic records.</p>
        </div>

        <div className="info-card">
          <h3>System Status</h3>
          <p className="status">● Backend Connected</p>
          <p>JWT Authentication Active</p>
        </div>
      </div>
    </>
  );
}

function StatCard({ icon, title, value }) {
  return (
    <div className="stat-card">
      <div className="stat-icon">{icon}</div>
      <div>
        <h3>{title}</h3>
        <strong>{value}</strong>
      </div>
    </div>
  );
}

/* ================= MODULE ================= */

function Module({ title, endpoint, token }) {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchData();
  }, [endpoint]);

  const fetchData = async () => {
    setLoading(true);
    setError("");

    try {
      const response = await axios.get(`${API}${endpoint}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setItems(getArray(response.data));
    } catch (err) {
      setError(
        err.response?.status === 403
          ? "Access denied. Your JWT may not have the required permission."
          : "Unable to load data from backend."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <section>
      <div className="module-header">
        <div>
          <h1>{title}</h1>
          <p>Manage {title.toLowerCase()} records</p>
        </div>

        <button className="refresh" onClick={fetchData}>
          🔄 Refresh
        </button>
      </div>

      {loading && <div className="loading">Loading...</div>}

      {error && <div className="error-box">{error}</div>}

      {!loading && !error && (
        <DataTable title={title} items={items} />
      )}
    </section>
  );
}

/* ================= TABLE ================= */

function DataTable({ title, items }) {
  if (!items.length) {
    return (
      <div className="empty">
        <div>📂</div>
        <h3>No {title.toLowerCase()} found</h3>
        <p>No records are currently available.</p>
      </div>
    );
  }

  const keys = Object.keys(items[0]).filter(
    (key) => typeof items[0][key] !== "object"
  );

  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            {keys.map((key) => (
              <th key={key}>{formatKey(key)}</th>
            ))}
          </tr>
        </thead>

        <tbody>
          {items.map((item, index) => (
            <tr key={item.id || index}>
              {keys.map((key) => (
                <td key={key}>{String(item[key] ?? "-")}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

/* ================= HELPERS ================= */

function getArray(response) {
  if (Array.isArray(response)) return response;

  if (Array.isArray(response?.data)) return response.data;

  if (Array.isArray(response?.data?.content))
    return response.data.content;

  if (Array.isArray(response?.content))
    return response.content;

  return [];
}

function formatKey(key) {
  return key
    .replace(/([A-Z])/g, " $1")
    .replace(/^./, (str) => str.toUpperCase());
}

function getIcon(item) {
  const icons = {
    Dashboard: "🏠",
    Students: "🎓",
    Faculty: "👨‍🏫",
    Courses: "📚",
    Attendance: "📊",
    Results: "📝",
    Notices: "📢",
  };

  return icons[item] || "•";
}

export default App;