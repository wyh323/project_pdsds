// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',
    password: ''
  },

  // 用户名输入
  onUsernameInput(e) {
    this.setData({ username: e.detail.value });
  },

  // 密码输入
  onPasswordInput(e) {
    this.setData({ password: e.detail.value });
  },

  // 登录事件
  onLogin() {
    const { username, password } = this.data;
    // 这里添加登录逻辑
    // 发送 POST 请求到后端
    wx.request({
      url: 'http://localhost:8080/patient/login',
      method: 'POST',
      data: {
        username,
        password
      },
      header: {
        'content-type': 'application/json' // 修改请求头
      },
      success: (res) => {
        console.log('登录响应:', res.data);
        if (res.data.code == 200) {
          const token = res.data.data;
          wx.setStorageSync('token', token);
          wx.showToast({
            title: '登录成功',
            icon: 'success',
            duration: 2000,
            success: () => { // 使用 success 回调
              setTimeout(() => {
                console.log('准备跳转到主页');
                wx.reLaunch({ url: '/pages/home/home' });
              }, 1000); // 总等待时间 ≈ 2秒（Toast） + 1秒（延迟）
            }
          });
        } else {
          wx.showToast({
            title: res.data.message || '登录失败',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: (err) => {
        console.error('登录请求失败:', err);
        wx.showToast({
          title: '网络请求错误',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },

  // 注册事件
  onRegister() {
    wx.navigateTo({ url: '/pages/register/register' });
  },

  // 忘记密码
  onForgotPassword() {
    wx.navigateTo({ url: '/pages/forgot-password/forgot-password' });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})