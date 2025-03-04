// pages/register/register.js
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

  // 注册事件
  onRegister() {
    const { username, password } = this.data;
    // 验证输入格式（前端验证）
    if (!username || username.length > 15 || username.trim().length === 0) {
      wx.showToast({
        title: '用户名格式不正确（1-15个字符）',
        icon: 'none',
        duration: 2000
      });
      return;
    }

    if (!password || password.length < 8 || password.length > 32 || password.trim().length === 0) {
      wx.showToast({
        title: '密码格式不正确（8-32个字符）',
        icon: 'none',
        duration: 2000
      });
      return;
    }
    // 这里添加注册逻辑
    wx.request({
      url: 'http://localhost:8080/patient/register',
      method: 'POST',
      data: { username, password },
      header: { 'content-type': 'application/json' },
      success: (res) => {
        console.log('注册响应:', res.data);
        if (res.data.code === 0) {
          wx.setStorageSync('token', res.data.data);
          wx.showToast({
            title: '注册成功',
            icon: 'success',
            duration: 2000,
            success: () => { // 使用 success 回调
              setTimeout(() => {
                console.log('准备跳转到登录页');
                wx.reLaunch({ url: '/pages/login/login' });
              }, 1000); // 总等待时间 ≈ 2秒（Toast） + 1秒（延迟）
            }
          });
        } else {
          wx.showToast({ title: res.data.message || '注册失败', icon: 'none', duration: 2000 });
        }
      },
      fail: (err) => {
        console.error('注册请求失败:', err);
        wx.showToast({ title: '网络请求错误', icon: 'none', duration: 2000 });
      }
    });
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